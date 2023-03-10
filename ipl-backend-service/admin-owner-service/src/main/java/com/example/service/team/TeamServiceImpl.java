package com.example.service.team;

import com.example.entity.Team;
import com.example.exceptionHandler.ExceptionErrorHandler;
import com.example.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public Team addTeam(Team team) throws ExceptionErrorHandler {

        if(team.getTeamName().isEmpty() || team.getTeamName().length()==0){

            throw new ExceptionErrorHandler("Team Name can not be empty !!!. Enter proper team name !!!");
        }else if(team.getOwnerName().isEmpty() || team.getOwnerName().length()==0){

            throw  new ExceptionErrorHandler("Owner name can not be empty !!!. Enter proper owner name !!!!");
        }
        try {
            return teamRepository.save(team);

        }catch (IllegalArgumentException e){

        throw new ExceptionErrorHandler("Team Details is null !!!" + e.getMessage());
        }catch(Exception e ){

        throw new ExceptionErrorHandler("Error in team service  !!!");
    }
    }

    @Override
    public void deleteTeam(Integer teamId) throws ExceptionErrorHandler {

        try {
            teamRepository.deleteById(teamId);

        }catch(Exception e){

            throw new ExceptionErrorHandler("Team Id is null !!! . Give valid team id!!!" + e.getMessage());
        }
    }

    @Override
    public List<Team> viewTeams() throws ExceptionErrorHandler {

        try {
            List<Team> allTeam = teamRepository.findAll();

            if(allTeam.isEmpty())
                throw new ExceptionErrorHandler("Team List is empty !!!");
            return allTeam;

        }catch(Exception e){
            throw  new ExceptionErrorHandler("Error in player service layer !!!"+ e.getMessage());
        }
    }

    @Override
    public Team getTeamById(Integer teamId) throws ExceptionErrorHandler {

        try {
            return teamRepository.findById(teamId).get();

        }catch(IllegalArgumentException e){

            throw new ExceptionErrorHandler("Team Id is  not found !!!"+ e.getMessage());
        }catch(NoSuchElementException e){

            throw new ExceptionErrorHandler("Team Id does not exist !!!" + e.getMessage());
        }
    }

    @Override
    public Team getTeamId(String ownerName) throws ExceptionErrorHandler {

        try {
            return teamRepository.getIdByName(ownerName);

        }catch(IllegalArgumentException e){
            throw new ExceptionErrorHandler("Owner name does not exist  !!!"+ e.getMessage());
        }
    }


}
