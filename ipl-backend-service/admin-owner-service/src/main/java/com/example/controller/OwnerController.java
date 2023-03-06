package com.example.controller;

import com.example.entity.Player;
import com.example.entity.Team;
import com.example.errors.ExceptionErrorHandler;
import com.example.service.player.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/owner/")
@RequiredArgsConstructor
public class OwnerController {

    @Autowired
    private final PlayerService playerService;

    @GetMapping("/view-players/{teamId}")
    public ResponseEntity<?> viewPlayerByOwnerId(@PathVariable Integer teamId){
        try {
            return new ResponseEntity<List<Player>>(playerService.getAllPlayer(teamId), HttpStatus.OK);
        }catch (ExceptionErrorHandler e){
            ExceptionErrorHandler ex = new ExceptionErrorHandler( e.getErrorMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ExceptionErrorHandler ex = new ExceptionErrorHandler( "Error in Owner Controller !!!" + e.getMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/add-player/{teamId}/{playerId}")
    public ResponseEntity<?> addPlayerToTeam(@PathVariable Integer teamId, @PathVariable Integer playerId){
        try {

            Team team = new Team();

            team.setTeamId(teamId);

            Player player = playerService.getPlayerById(playerId);

            player.setAvailable(false);
            player.setTeam(team);
            playerService.addPlayer(player);

            return new ResponseEntity<String>("Player added successfully !!!",HttpStatus.OK);
        }catch (ExceptionErrorHandler e){
            ExceptionErrorHandler ex = new ExceptionErrorHandler( e.getErrorMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ExceptionErrorHandler ex = new ExceptionErrorHandler( "Error in Owner Controller !!!" + e.getMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete-player/{playerId}")
    public ResponseEntity<?> deletePlayerFromTeam(@PathVariable Integer playerId){

        try {

            Player player = playerService.getPlayerById(playerId);
            player.setAvailable(true);
            player.setTeam(null);
            playerService.addPlayer(player);
            return new ResponseEntity<String>("Player Removed from Team !!!",HttpStatus.OK);
        }catch (ExceptionErrorHandler e){
            ExceptionErrorHandler ex = new ExceptionErrorHandler( e.getErrorMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ExceptionErrorHandler ex = new ExceptionErrorHandler( "Error in Owner Controller !!!" + e.getMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
