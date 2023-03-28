package com.example.service.player;

import com.example.entity.Player;
import com.example.exceptions.PlayerNotFoundException;

import java.util.List;

public interface PlayerService {

    public Player addPlayer(Player player) throws  PlayerNotFoundException;
    public void deletePlayer(Integer id) ;
    public List<Player> viewPlayers() throws  PlayerNotFoundException;
    public Player getPlayerById(Integer teamId);

    public List<Player> getAllPlayer(Integer teamId) ;
}
