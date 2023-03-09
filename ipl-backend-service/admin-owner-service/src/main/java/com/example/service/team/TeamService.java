package com.example.service.team;

import com.example.entity.Team;
import com.example.exceptionHandler.ExceptionErrorHandler;

import java.util.List;

public interface TeamService {

    public Team addTeam(Team team) throws ExceptionErrorHandler;
    public void deleteTeam(Integer id) throws ExceptionErrorHandler;
    public List<Team> viewTeams() throws ExceptionErrorHandler;

    public Team getTeamById(Integer id) throws ExceptionErrorHandler;

    public  Team getTeamId(String ownerName) throws ExceptionErrorHandler;





}
