package com.example.service.player;

import com.example.entity.Player;
import com.example.exceptions.PlayerNotFoundException;
import com.example.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Player addPlayer(Player player) throws PlayerNotFoundException {

        if(player.getPlayerName().isEmpty() || player.getPlayerName().length()== 0 ){

            throw new PlayerNotFoundException("Player name can not be empty!!!" );

        }else if(player.getAge()== null || player.getAge()== 0 ){

            throw new PlayerNotFoundException("Age can not be empty!!!" );
        }

           return playerRepository.save(player);

    }

    @Override
    public void deletePlayer(Integer playerId) {

            playerRepository.deleteById(playerId);

    }

    @Override
    public List<Player> viewPlayers() throws PlayerNotFoundException {


            List<Player> allPlayer = playerRepository.findAll();
            if(allPlayer.isEmpty()) {
                throw new PlayerNotFoundException("Player List is empty !!!");
            }
            return  allPlayer;

    }

    @Override
    public Player getPlayerById(Integer playerId)  {

            return playerRepository.findById(playerId).get();


    }

    @Override
    public List<Player> getAllPlayer(Integer teamId)  {

            return playerRepository.getByTeamId(teamId);

    }
}
