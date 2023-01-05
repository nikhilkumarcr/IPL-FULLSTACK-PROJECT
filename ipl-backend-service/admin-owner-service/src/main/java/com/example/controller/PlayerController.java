package com.example.controller;

import com.example.entity.Player;
import com.example.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/players/")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerRepository playerRepository;

    @PostMapping("/add-player")
      public ResponseEntity<?> addPlayer(@RequestBody Player player){

//          Player player = new Player();
//          Team team = new Team();
//
//          player.setPlayerName(playerRequest.getPlayerName());
//          player.setAge(playerRequest.getAge());
//          player.setSpecialty(playerRequest.getSpecialty());
//          player.setIsForegin(playerRequest.getForegin());
//          player.setImageUrl(playerRequest.getImageUrl());
//          player.setNationality(playerRequest.getNationality());
//          player.setIsAvailable(playerRequest.getAvailable());
         // team.getTeamId();
        //  player.setTeam(team.getTeamId());
           playerRepository.save(player);


          return  ResponseEntity.status(HttpStatus.CREATED).body("added");

      }

}
