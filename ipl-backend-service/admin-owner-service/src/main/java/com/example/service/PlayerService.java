package com.example.service;

import com.example.entity.Player;
import com.example.entity.Team;

import java.util.List;

public interface PlayerService {

    public Player addPlayer(Player player);
    public void deletePlayer(Integer id);
    public List<Player> viewPlayers();
    public Player getPlayerById(Integer id);
}
