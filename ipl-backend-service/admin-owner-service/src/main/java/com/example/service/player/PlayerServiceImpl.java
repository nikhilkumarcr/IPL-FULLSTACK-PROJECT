package com.example.service.player;

import com.example.entity.Player;
import com.example.exceptionHandler.ExceptionErrorHandler;
import com.example.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Player addPlayer(Player player) throws ExceptionErrorHandler {

        if(player.getPlayerName().isEmpty() || player.getPlayerName().length()== 0 ){
            throw new ExceptionErrorHandler("Player name can not be empty!!!" );
        }

       try {
           return playerRepository.save(player);
       }catch (IllegalArgumentException e){
           throw new ExceptionErrorHandler("Player details is null" + e.getMessage());
       }catch(Exception e ){
           throw new ExceptionErrorHandler("Error in player service layer !!!");
       }
    }

    @Override
    public void deletePlayer(Integer playerId) throws ExceptionErrorHandler {
        try {
            playerRepository.deleteById(playerId);
        }catch(Exception e){

            throw new ExceptionErrorHandler("Player Id is null . Give valid player id!!!" + e.getMessage());
        }
    }

    @Override
    public List<Player> viewPlayers() throws ExceptionErrorHandler {

        try {
            List<Player> allPlayer = playerRepository.findAll();
            if(allPlayer.isEmpty())
                throw new ExceptionErrorHandler("Player List is empty !!!");
            return allPlayer;
        }catch(Exception e){
            throw  new ExceptionErrorHandler("Error in player service layer !!!"+ e.getMessage());
        }
    }

    @Override
    public Player getPlayerById(Integer playerId) throws ExceptionErrorHandler {
        try {
            return playerRepository.findById(playerId).get();
        }catch(IllegalArgumentException e){

            throw new ExceptionErrorHandler("Player Id is  not found !!!"+ e.getMessage());
        }catch(NoSuchElementException e){

            throw new ExceptionErrorHandler("Player Id does not exist !!!" + e.getMessage());
        }

    }

    @Override
    public List<Player> getAllPlayer(Integer teamId) throws ExceptionErrorHandler {

        try {

            return playerRepository.getByTeamId(teamId);

        }catch(IllegalArgumentException e){

            throw new ExceptionErrorHandler("Player Id is  not found !!!"+ e.getMessage());
        }catch(NoSuchElementException e){

            throw new ExceptionErrorHandler("Player Id does not exist !!!" + e.getMessage());
        }
    }
}
