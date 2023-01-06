package com.example.controller;

import com.example.dto.PlayerRequest;
import com.example.dto.PlayerResponse;
import com.example.dto.TeamRequest;
import com.example.entity.Player;
import com.example.entity.Team;
import com.example.repository.PlayerRepository;
import com.example.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/players/")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/view-players")
    public ResponseEntity<?> viewPlayers(){

        List<Player> players = playerService.viewPlayers();

        List<PlayerResponse> playerResponses = players
                .stream()
                .map(player -> new PlayerResponse(player.getPlayerId(),player.getPlayerName(),player.getAge(),player.getSpecialty(),player.getIsForegin(),
                  player.getIsAvailable(),player.getImageUrl(),player.getNationality()))
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

    @PostMapping("/update-team/{id}")
    public ResponseEntity<?> updateTeam(@RequestBody PlayerRequest playerRequest, @PathVariable Integer id){
        Player player = playerService.getPlayerById(id);

        playerService.addPlayer(player);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }


}
