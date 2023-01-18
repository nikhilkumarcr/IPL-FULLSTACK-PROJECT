package com.example.controller;

import com.example.dto.PlayerRequest;
import com.example.dto.PlayerResponse;
import com.example.entity.Player;
import com.example.service.player.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class PlayerController {

    @Autowired
    private final PlayerService playerService;

    @GetMapping("/view-players")
    public ResponseEntity<?> viewPlayers(){

        List<Player> players = playerService.viewPlayers();

        List<PlayerResponse> playerResponses = players
                .stream()
                .map(player -> new PlayerResponse(player.getPlayerId(),player.getPlayerName(),player.getAge(),player.getSpecialty(),player.getImageUrl(),player.getNationality(),player.getAvailable(),player.getTeam()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(playerResponses);
    }


    @PostMapping("/add-player")
    public ResponseEntity<?> addPlayer(@RequestBody PlayerRequest playerRequest){

         Player player = new Player();

         player.setPlayerName(playerRequest.getPlayerName());
         player.setAge(playerRequest.getAge());
         player.setSpecialty(playerRequest.getSpecialty());
         player.setNationality(playerRequest.getNationality());
         player.setImageUrl(playerRequest.getImageUrl());
         player.setAvailable(true);

         player =  playerService.addPlayer(player);

         PlayerResponse playerResponse = new PlayerResponse();

         playerResponse.setPlayerId(player.getPlayerId());
         playerResponse.setPlayerName(player.getPlayerName());
         playerResponse.setAge(player.getAge());
         playerResponse.setSpecialty(player.getSpecialty());
         playerResponse.setNationality(player.getNationality());
         playerResponse.setImageUrl(player.getImageUrl());
         playerResponse.setAvailable(player.getAvailable());

         return  ResponseEntity.status(HttpStatus.CREATED).body(playerResponse);
    }

    @DeleteMapping("/delete-player/{playerId}")
    public  ResponseEntity<?> delete(@PathVariable Integer playerId){
       playerService.deletePlayer(playerId);
        return  ResponseEntity.status(HttpStatus.OK).body("Player removed !!!");
    }

    @GetMapping("/get-player/{playerId}")
    public ResponseEntity<?>  getTeam(@PathVariable Integer playerId){
        Player player = playerService.getPlayerById(playerId);

        PlayerResponse playerResponse = new PlayerResponse();

        playerResponse.setPlayerId(player.getPlayerId());
        playerResponse.setPlayerName(player.getPlayerName());
        playerResponse.setAge(player.getAge());
        playerResponse.setSpecialty(player.getSpecialty());
        playerResponse.setImageUrl(player.getImageUrl());
        playerResponse.setNationality(player.getNationality());
        playerResponse.setAvailable(player.getAvailable());

        return ResponseEntity.status(HttpStatus.OK).body(playerResponse);
    }


    @PostMapping("/update-player/{playerId}")
    public ResponseEntity<?> updatePlayer(@RequestBody PlayerRequest playerRequest, @PathVariable Integer playerId){
        Player player = playerService.getPlayerById(playerId);

        player.setPlayerName(playerRequest.getPlayerName());
        player.setAge(playerRequest.getAge());
        player.setSpecialty(playerRequest.getSpecialty());
        player.setNationality(playerRequest.getNationality());
        player.setImageUrl(playerRequest.getImageUrl());
        player.setAvailable(true);

        playerService.addPlayer(player);
        return ResponseEntity.status(HttpStatus.OK).body("Updated Player !!!");
    }


}
