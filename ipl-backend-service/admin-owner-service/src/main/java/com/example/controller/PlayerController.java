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

@RestController
@RequestMapping("api/players/")
@RequiredArgsConstructor
public class PlayerController {

    @Autowired
    private final PlayerService playerService;

    @GetMapping("/view-players")
    public ResponseEntity<?> viewPlayers(){

        List<Player> players = playerService.viewPlayers();

        List<PlayerResponse> playerResponses = players
                .stream()
                .map(player -> new PlayerResponse(player.getPlayerId(),player.getPlayerName(),player.getAge(),player.getSpecialty(),player.getForegin(),
                  player.getAvailable(),player.getImageUrl(),player.getNationality()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(playerResponses);
    }


    @PostMapping("/add-player")
    public ResponseEntity<?> addPlayer(@RequestBody Player player){
          playerService.addPlayer(player);
          return  ResponseEntity.status(HttpStatus.CREATED).body("added");
    }

    @DeleteMapping("/delete-player/{id}")
    public  ResponseEntity<?> delete(@PathVariable Integer id){
       playerService.deletePlayer(id);
        return  ResponseEntity.status(HttpStatus.OK).body("Player removed !!!");
    }

    @GetMapping("/get-player/{id}")
    public ResponseEntity<?>  getTeam(@PathVariable Integer id){
        Player player = playerService.getPlayerById(id);

        PlayerResponse playerResponse = new PlayerResponse();
        playerResponse.setPlayerId(player.getPlayerId());
        playerResponse.setPlayerName(player.getPlayerName());
        playerResponse.setAge(player.getAge());
        playerResponse.setForeign(player.getForegin());
        playerResponse.setIsAvailable(player.getAvailable());
        playerResponse.setImageUrl(player.getImageUrl());
        playerResponse.setNationality(player.getNationality());

        return ResponseEntity.status(HttpStatus.OK).body(playerResponse);
    }


    @PostMapping("/update-player/{id}")
    public ResponseEntity<?> updatePlayer(@RequestBody PlayerRequest playerRequest, @PathVariable Integer id){
        Player player = playerService.getPlayerById(id);
        playerService.addPlayer(player);
        return ResponseEntity.status(HttpStatus.OK).body("Updated Player !!!");
    }


}
