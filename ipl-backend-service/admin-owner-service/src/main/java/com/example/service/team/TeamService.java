package com.example.service.team;

import com.example.entity.Team;

import java.util.List;

public interface TeamService {

    public Team addTeam(Team team);
    public void deleteTeam(Integer id);
    public List<Team> viewTeams();

    public Team getTeamById(Integer id);

    public  Team getTeamId(String ownerName);

    public Team addTeamDetails(Team team);




}
