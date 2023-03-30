package com.example.controller;

import com.example.dto.*;
import com.example.entity.Player;
import com.example.entity.Team;
import com.example.exceptions.PlayerNotFoundException;
import com.example.exceptions.TeamNotFoundException;
import com.example.service.player.PlayerService;
import com.example.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class TeamController {

    @Autowired
    private final TeamService teamService;
    @Autowired
    private final PlayerService playerService;
    @Autowired
    private final RestTemplate restTemplate;

    @GetMapping("/view-teams")
    public ResponseEntity<List<TeamResponse>> viewTeams() throws TeamNotFoundException {

            List<Team> iplTeams = teamService.viewTeams();
            List<TeamResponse> teamResponses = iplTeams
                    .stream()
                    .map(team -> new TeamResponse(team.getTeamId(), team.getTeamName(), team.getOwnerName(), team.getCity(), team.getState(), team.getEmailId(), team.getTeamUrl()))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(teamResponses);

    }
    @PostMapping("/add-team")
    public ResponseEntity<Team>  addTeam(@RequestBody TeamRequest teamRequest) throws TeamNotFoundException {

            Team team = new Team();

            team.setTeamName(teamRequest.getTeamName());
            team.setOwnerName(teamRequest.getOwnerName());
            team.setCity(teamRequest.getCity());
            team.setState(teamRequest.getState());
            team.setEmailId(teamRequest.getEmailId());
            team.setTempPassword("123456");
            team.setTeamUrl(teamRequest.getTeamUrl());

            team = teamService.addTeam(team);

            OwnerDetails ownerDetails = new OwnerDetails();
            ownerDetails.setUsername(team.getOwnerName());
            ownerDetails.setEmail(team.getEmailId());
            ownerDetails.setPassword(team.getTempPassword());

            OwnerDetails ownerDetail = restTemplate.postForObject("http://localhost:8081/api/auth/sign-up", ownerDetails, OwnerDetails.class);

            return new ResponseEntity<Team>(team, HttpStatus.CREATED);

    }

     @DeleteMapping("/delete-team/{teamId}")
     public ResponseEntity<String> deleteTeam(@PathVariable Integer teamId) throws  PlayerNotFoundException {

            List<Player> players = playerService.getAllPlayer(teamId);

            for (Player player : players) {
                player.setTeam(null);
                playerService.addPlayer(player);
            }

            teamService.deleteTeam(teamId);
            return new ResponseEntity<String>("Team Removed !!!",HttpStatus.OK);

    }


    @GetMapping("/get-team/{teamId}")
    public ResponseEntity<TeamResponse> getTeamDetails(@PathVariable Integer teamId)  {

            Team team = teamService.getTeamById(teamId);

            TeamResponse teamResponse = new TeamResponse();

            teamResponse.setTeamId(team.getTeamId());
            teamResponse.setTeamName(team.getTeamName());
            teamResponse.setOwnerName(team.getOwnerName());
            teamResponse.setEmailId(team.getEmailId());
            teamResponse.setCity(team.getCity());
            teamResponse.setState(team.getState());
            teamResponse.setTeamUrl(team.getTeamUrl());

            return ResponseEntity.status(HttpStatus.OK).body(teamResponse);


    }

    @PostMapping("/update-team/{teamId}")
    public ResponseEntity<String> updateTeam(@RequestBody TeamRequest teamRequest, @PathVariable Integer teamId) throws TeamNotFoundException {

            Team team = teamService.getTeamById(teamId);

            team.setTeamName(teamRequest.getTeamName());
            team.setCity(teamRequest.getCity());
            team.setState(teamRequest.getState());
            team.setTeamUrl(teamRequest.getTeamUrl());

            teamService.addTeam(team);

            return ResponseEntity.status(HttpStatus.OK).body("Team Details Updated!!!");

    }


    @GetMapping("/get-teamId/{ownerName}")
    public ResponseEntity<TeamDetails> getTeamId(@PathVariable String ownerName)  {

            Team team = teamService.getTeamId(ownerName);
            TeamDetails teamDetails = new TeamDetails();
            teamDetails.setTeamId(team.getTeamId());
            teamDetails.setTeamName(team.getTeamName());

            return ResponseEntity.status(HttpStatus.OK).body(teamDetails);

    }

}
