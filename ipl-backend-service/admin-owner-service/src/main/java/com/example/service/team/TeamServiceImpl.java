package com.example.service.team;

import com.example.entity.Team;
import com.example.exceptions.NotFoundException;
import com.example.repository.TeamRepository;
import com.example.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public Team addTeam(Team team) {
       return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Integer teamId) {
        teamRepository.deleteById(teamId);
    }

    @Override
    public List<Team> viewTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(Integer teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(()->new NotFoundException("No Team found for this TeamId" + teamId));
    }

    @Override
    public Team getTeamId(String ownerName) {
        return teamRepository.getIdByName(ownerName);
    }

    @Override
    public Team addTeamDetails(Team team) {
        return teamRepository.save(team);
    }

}
