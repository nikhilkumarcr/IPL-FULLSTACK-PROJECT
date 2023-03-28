package com.example.service.team;

import com.example.entity.Team;
import com.example.exceptions.TeamNotFoundException;
import com.example.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public Team addTeam(Team team) throws TeamNotFoundException {

        if(team.getTeamName().isEmpty() || team.getTeamName().length()==0){

            throw new TeamNotFoundException("Team Name can not be empty !!!. Enter valid Team Name !!!");
        }else if(team.getOwnerName().isEmpty() || team.getOwnerName().length()==0){

            throw  new TeamNotFoundException("Owner name can not be empty !!!. Enter valid Owner Name !!!!");
        }else if(team.getEmailId().isEmpty() || team.getEmailId().length()==0){

            throw  new TeamNotFoundException("Email Id can not be empty !!!. Enter valid Email Id !!!!");
        }

            return teamRepository.save(team);

    }

    @Override
    public void deleteTeam(Integer teamId)  {

            teamRepository.deleteById(teamId);
    }

    @Override
    public List<Team> viewTeams() throws  TeamNotFoundException {

        try {
            List<Team> allTeam = teamRepository.findAll();

            if(allTeam.isEmpty())
                throw new TeamNotFoundException("Team List is empty !!!");
            return allTeam;

        }catch(Exception e){
            throw  new TeamNotFoundException("Error in player service layer !!!"+ e.getMessage());
        }
    }

    @Override
    public Team getTeamById(Integer teamId)  {


            return teamRepository.findById(teamId).get();

    }

    @Override
    public Team getTeamId(String ownerName)  {

            return teamRepository.getIdByName(ownerName);

    }


}
