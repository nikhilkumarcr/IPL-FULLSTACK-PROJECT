package com.example.service.player;

import com.example.entity.Player;
import com.example.entity.Team;

import java.util.List;

public interface PlayerService {

    public Player addPlayer(Player player);
    public void deletePlayer(Integer id);
    public List<Player> viewPlayers();
    public Player getPlayerById(Integer teamId);

    public List<Player> getAllPlayer(Integer teamId);
}