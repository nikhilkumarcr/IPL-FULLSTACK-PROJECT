package com.example.controller;

import com.example.entity.Player;
import com.example.entity.Team;
import com.example.service.player.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner/")
@RequiredArgsConstructor
public class OwnerController {

    @Autowired
    private final PlayerService playerService;

    @GetMapping("/view-players/{teamId}")
    public List<Player> viewPlayerByOwnerId(@PathVariable Integer teamId){
        return  playerService.getAllPlayer(teamId);
    }


    @PostMapping("/add-player/{teamId}/{playerId}")
    public ResponseEntity<?> addPlayerToTeam(@PathVariable Integer teamId, @PathVariable Integer playerId){
         Team team = new Team();

         team.setTeamId(teamId);
         Player player = playerService.getPlayerById(playerId);

         player.setTeam(team);
         playerService.addPlayer(player);

         return  ResponseEntity.status(HttpStatus.CREATED).body("Player Added to Owner-Team!!!!");
    }


    @DeleteMapping("/delete-player/{playerId}")
    public ResponseEntity<?> deletePlayerFromTeam(@PathVariable Integer playerId){
        Player player = playerService.getPlayerById(playerId);
        player.setTeam(null);
        playerService.addPlayer(player);
        return  ResponseEntity.status(HttpStatus.OK).body("Player Removed For Team !!!");
    }


}
