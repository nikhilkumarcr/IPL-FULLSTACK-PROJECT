package com.example.controller;

import com.example.entity.Player;
import com.example.entity.Team;
import com.example.exceptions.PlayerNotFoundException;
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
    public ResponseEntity<List<Player>> viewPlayerByOwnerId(@PathVariable Integer teamId)  {

            return new ResponseEntity<List<Player>>(playerService.getAllPlayer(teamId), HttpStatus.OK);

    }


    @PostMapping("/add-player/{teamId}/{playerId}")
    public ResponseEntity<String> addPlayerToTeam(@PathVariable Integer teamId, @PathVariable Integer playerId) throws  PlayerNotFoundException {

            Team team = new Team();

            team.setTeamId(teamId);

            Player player = playerService.getPlayerById(playerId);

            player.setAvailable(false);
            player.setTeam(team);
            playerService.addPlayer(player);

            return new ResponseEntity<String>("Player added successfully !!!",HttpStatus.OK);

    }


    @DeleteMapping("/delete-player/{playerId}")
    public ResponseEntity<String> deletePlayerFromTeam(@PathVariable Integer playerId) throws  PlayerNotFoundException {

            Player player = playerService.getPlayerById(playerId);
            player.setAvailable(true);
            player.setTeam(null);
            playerService.addPlayer(player);
            return new ResponseEntity<String>("Player Removed from Team !!!",HttpStatus.OK);


    }


}
