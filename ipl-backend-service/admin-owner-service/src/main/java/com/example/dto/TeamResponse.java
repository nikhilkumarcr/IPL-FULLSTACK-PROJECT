package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponse {

    private Integer teamId;
    private String teamName;
    private String ownerName;
    private String city;
    private String state;
    private String emailId;
    private String teamUrl;
}
