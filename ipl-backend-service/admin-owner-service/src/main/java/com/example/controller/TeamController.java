package com.example.controller;

import com.example.dto.*;
import com.example.entity.Player;
import com.example.entity.Team;
import com.example.exceptionHandler.ExceptionErrorHandler;
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
    public ResponseEntity<?> viewTeams(){

        try {
            List<Team> iplTeams = teamService.viewTeams();
            List<TeamResponse> teamResponses = iplTeams
                    .stream()
                    .map(team -> new TeamResponse(team.getTeamId(), team.getTeamName(), team.getOwnerName(), team.getCity(), team.getState(), team.getEmailId(), team.getTeamUrl()))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(teamResponses);

        }catch (ExceptionErrorHandler e){
            ExceptionErrorHandler ex = new ExceptionErrorHandler( e.getErrorMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ExceptionErrorHandler ex = new ExceptionErrorHandler( "Error in Team Controller !!!" + e.getMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/add-team")
    public ResponseEntity<?>  addTeam(@RequestBody TeamRequest teamRequest){

        try {
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

        }catch (ExceptionErrorHandler e){

            ExceptionErrorHandler ex = new ExceptionErrorHandler( e.getErrorMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){

            ExceptionErrorHandler ex = new ExceptionErrorHandler( "Error in Team Controller !!!" + e.getMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
    }

     @DeleteMapping("/delete-team/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable Integer teamId){

        try {

            List<Player> players = playerService.getAllPlayer(teamId);

            for (Player player : players) {
                player.setTeam(null);
                playerService.addPlayer(player);
            }

            teamService.deleteTeam(teamId);
            return new ResponseEntity<String>("Team Removed !!!",HttpStatus.OK);
        }catch (ExceptionErrorHandler e){

            ExceptionErrorHandler ex = new ExceptionErrorHandler( e.getErrorMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){

            ExceptionErrorHandler ex = new ExceptionErrorHandler("Error in Team Controller !!!" + e.getMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/get-team/{teamId}")
    public ResponseEntity<?> getTeamDetails(@PathVariable Integer teamId){

        try {
            Team team = teamService.getTeamById(teamId);

            TeamResponse teamResponse = new TeamResponse();
            teamResponse.setTeamId(team.getTeamId());
            teamResponse.setTeamName(team.getTeamName());
            teamResponse.setCity(team.getCity());
            teamResponse.setState(team.getState());
            teamResponse.setTeamUrl(team.getTeamUrl());
            return ResponseEntity.status(HttpStatus.OK).body(teamResponse);
        }catch (ExceptionErrorHandler e){

            ExceptionErrorHandler ex = new ExceptionErrorHandler( e.getErrorMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){

            ExceptionErrorHandler ex = new ExceptionErrorHandler( "Error in Team Controller !!!" + e.getMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update-team/{teamId}")
    public ResponseEntity<?> updateTeam(@RequestBody TeamRequest teamRequest, @PathVariable Integer teamId){

        try {
            Team team = teamService.getTeamById(teamId);

            team.setTeamName(teamRequest.getTeamName());
            team.setCity(teamRequest.getCity());
            team.setState(teamRequest.getState());
            team.setTeamUrl(teamRequest.getTeamUrl());

            teamService.addTeam(team);

            return ResponseEntity.status(HttpStatus.OK).body("Team Details Updated!!!");
        }catch (ExceptionErrorHandler e){

            ExceptionErrorHandler ex = new ExceptionErrorHandler( e.getErrorMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){

            ExceptionErrorHandler ex = new ExceptionErrorHandler("Error in Team Controller !!!" + e.getMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/get-teamId/{ownerName}")
    public ResponseEntity<?> getTeamId(@PathVariable String ownerName){

        try {

            Team team = teamService.getTeamId(ownerName);
            TeamDetails teamDetails = new TeamDetails();
            teamDetails.setTeamId(team.getTeamId());
            teamDetails.setTeamName(team.getTeamName());

            return ResponseEntity.status(HttpStatus.OK).body(teamDetails);

        }catch (ExceptionErrorHandler e){
            ExceptionErrorHandler ex = new ExceptionErrorHandler( e.getErrorMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ExceptionErrorHandler ex = new ExceptionErrorHandler("Error in Team Controller !!!" + e.getMessage());
            return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
