package com.example.controller;

import com.example.dto.TeamRequest;
import com.example.entity.Team;
import com.example.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/team/")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/add-team")
    public ResponseEntity<?>  addTeam(@RequestBody TeamRequest teamRequest){
        Team team = new Team();
        team.setTeamName(teamRequest.getTeamName());
        team.setOwnerName(teamRequest.getOwnerName());
        team.setCity(teamRequest.getCity());
        team.setState(teamRequest.getState());
        team.setEmailId(teamRequest.getEmailId());
        team.setTempPassword("123456");
        team = teamService.addTeam(team);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Added");
    }

     @DeleteMapping("/delete-team/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Integer id){
        teamService.deleteTeam(id);
        return ResponseEntity.status(HttpStatus.OK).body("Team Removed");
    }






}
