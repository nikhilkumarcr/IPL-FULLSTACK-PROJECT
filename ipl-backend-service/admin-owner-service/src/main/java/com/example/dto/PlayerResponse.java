package com.example.dto;

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
    private Boolean foreign;
    private Boolean isAvailable;
    private String imageUrl;
    private String nationality;



}
