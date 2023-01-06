package com.example.controller;

import com.example.dto.TeamRequest;
import com.example.dto.TeamResponse;
import com.example.entity.Team;
import com.example.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/team/")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/view-teams")
    public ResponseEntity<?>  viewTeams(){
        List<Team> iplTeams = teamService.viewTeams();
        List<TeamResponse> teamResponses = iplTeams
                .stream()
                .map(team-> new TeamResponse(team.getTeamId(),team.getTeamName(),team.getOwnerName(),team.getCity(),team.getState(),team.getEmailId()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(teamResponses);
    }
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

    @GetMapping("/get-team/{id}")
    public ResponseEntity<?>  getTeam(@PathVariable Integer id){
        Team team = teamService.getTeamById(id);
        TeamResponse teamResponse = new TeamResponse();
        teamResponse.setTeamId(team.getTeamId());
        teamResponse.setTeamName(team.getTeamName());
        teamResponse.setOwnerName(team.getOwnerName());
        teamResponse.setCity(team.getCity());
        teamResponse.setState(team.getState());
        teamResponse.setEmailId(team.getEmailId());

        return ResponseEntity.status(HttpStatus.OK).body(teamResponse);
    }






}
