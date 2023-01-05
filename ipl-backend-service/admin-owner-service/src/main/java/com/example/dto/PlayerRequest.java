package com.example.dto;

import lombok.Data;

@Data
public class PlayerRequest {

    private String playerName;
    private Integer age;
    private String specialty;
    private Boolean foregin;
    private Boolean available;
    private String imageUrl;
    private String nationality;

  //  private Team team;
}
