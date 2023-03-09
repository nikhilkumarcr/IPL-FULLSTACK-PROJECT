package com.example.service.player;

import com.example.entity.Player;
import com.example.exceptionHandler.ExceptionErrorHandler;

import java.util.List;

public interface PlayerService {

    public Player addPlayer(Player player) throws ExceptionErrorHandler;
    public void deletePlayer(Integer id) throws ExceptionErrorHandler;
    public List<Player> viewPlayers() throws ExceptionErrorHandler;
    public Player getPlayerById(Integer teamId) throws ExceptionErrorHandler;

    public List<Player> getAllPlayer(Integer teamId) throws ExceptionErrorHandler;
}
