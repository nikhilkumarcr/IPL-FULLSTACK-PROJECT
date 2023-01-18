package com.example.service.player;

import com.example.entity.Player;
import com.example.exceptions.NotFoundException;
import com.example.repository.PlayerRepository;
import com.example.service.player.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void deletePlayer(Integer playerId) {
        playerRepository.deleteById(playerId);
    }

    @Override
    public List<Player> viewPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player getPlayerById(Integer playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(()->new NotFoundException("No Player found for this PlayerId" + playerId));
    }

    @Override
    public List<Player> getAllPlayer(Integer teamId) {
        return playerRepository.getByTeamId(teamId);
    }
}
