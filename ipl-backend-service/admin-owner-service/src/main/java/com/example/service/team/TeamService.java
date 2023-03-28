package com.example.service.team;

import com.example.entity.Team;

import com.example.exceptions.TeamNotFoundException;

import java.util.List;

public interface TeamService {

    public Team addTeam(Team team) throws TeamNotFoundException;
    public void deleteTeam(Integer id) ;
    public List<Team> viewTeams() throws  TeamNotFoundException;

    public Team getTeamById(Integer id);

    public  Team getTeamId(String ownerName) ;





}
