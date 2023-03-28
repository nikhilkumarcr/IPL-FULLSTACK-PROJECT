package com.example.controller;

import com.example.dto.PlayerRequest;
import com.example.dto.PlayerResponse;
import com.example.entity.Player;
import com.example.exceptions.PlayerNotFoundException;
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
    public ResponseEntity<List<PlayerResponse>> viewPlayers() throws PlayerNotFoundException {

            List<Player> players = playerService.viewPlayers();

            List<PlayerResponse> playerResponses = players
                    .stream()
                    .map(player -> new PlayerResponse(player.getPlayerId(), player.getPlayerName(), player.getAge(), player.getSpecialty(), player.getImageUrl(), player.getNationality(), player.getAvailable(), player.getTeam()))
                    .collect(Collectors.toList());
            return new ResponseEntity<List<PlayerResponse>>(playerResponses, HttpStatus.OK);


    }


    @PostMapping("/add-player")
    public ResponseEntity<PlayerResponse> addPlayer(@RequestBody PlayerRequest playerRequest) throws PlayerNotFoundException {

            Player player = new Player();

            player.setPlayerName(playerRequest.getPlayerName());
            player.setAge(playerRequest.getAge());
            player.setSpecialty(playerRequest.getSpecialty());
            player.setNationality(playerRequest.getNationality());
            player.setImageUrl(playerRequest.getImageUrl());
            player.setAvailable(true);

            player = playerService.addPlayer(player);

            PlayerResponse playerResponse = new PlayerResponse();

            playerResponse.setPlayerId(player.getPlayerId());
            playerResponse.setPlayerName(player.getPlayerName());
            playerResponse.setAge(player.getAge());
            playerResponse.setSpecialty(player.getSpecialty());
            playerResponse.setNationality(player.getNationality());
            playerResponse.setAvailable(player.getAvailable());

            return new ResponseEntity<PlayerResponse>(playerResponse, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete-player/{playerId}")
    public  ResponseEntity<String> delete(@PathVariable Integer playerId) {

         playerService.deletePlayer(playerId);
         return new ResponseEntity<String>("Player deleted from the player details", HttpStatus.OK);

    }

    @GetMapping("/get-player/{playerId}")
    public ResponseEntity<PlayerResponse>  getTeam(@PathVariable Integer playerId)  {

            Player player = playerService.getPlayerById(playerId);

            PlayerResponse playerResponse = new PlayerResponse();

            playerResponse.setPlayerId(player.getPlayerId());
            playerResponse.setPlayerName(player.getPlayerName());
            playerResponse.setAge(player.getAge());
            playerResponse.setSpecialty(player.getSpecialty());
            playerResponse.setNationality(player.getNationality());
            playerResponse.setAvailable(player.getAvailable());
            playerResponse.setImageUrl(player.getImageUrl());

            return new ResponseEntity<PlayerResponse>(playerResponse, HttpStatus.OK);


    }


    @PostMapping("/update-player/{playerId}")
    public ResponseEntity<PlayerResponse> updatePlayer(@RequestBody PlayerRequest playerRequest, @PathVariable Integer playerId) throws PlayerNotFoundException {

            Player player = playerService.getPlayerById(playerId);

            player.setPlayerName(playerRequest.getPlayerName());
            player.setAge(playerRequest.getAge());
            player.setSpecialty(playerRequest.getSpecialty());
            player.setNationality(playerRequest.getNationality());
            player.setImageUrl(playerRequest.getImageUrl());
            player.setAvailable(true);

            player = playerService.addPlayer(player);

            PlayerResponse playerResponse = new PlayerResponse();
            playerResponse.setPlayerId(player.getPlayerId());
            playerResponse.setPlayerName(player.getPlayerName());
            playerResponse.setAge(player.getAge());
            playerResponse.setSpecialty(player.getSpecialty());
            playerResponse.setNationality(player.getNationality());
            playerResponse.setImageUrl(player.getImageUrl());
            playerResponse.setAvailable(player.getAvailable());
            playerResponse.setTeam(player.getTeam());
            return new ResponseEntity<PlayerResponse>(playerResponse, HttpStatus.OK);


    }

}
