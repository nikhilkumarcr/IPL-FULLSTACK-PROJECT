package com.example.dto;

import com.example.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponse {

    private Integer playerId;
    private String playerName;
    private Integer age;
    private String specialty;
    private String imageUrl;
    private String nationality;
    private Boolean available;

    private Team team;



}
