package com.example.service;

import com.example.entity.Team;

import java.util.List;

public interface TeamService {

    public Team addTeam(Team team);
    public void deleteTeam(Integer id);
    public List<Team> viewTeams();

    public Team getTeamById(Integer id);
    public Integer findidByName(String teamName);




}
