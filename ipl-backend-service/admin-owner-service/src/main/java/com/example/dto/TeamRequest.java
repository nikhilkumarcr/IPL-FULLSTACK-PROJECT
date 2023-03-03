package com.example.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TeamRequest {

    @NotEmpty(message = "Team Name can not be null")
    private String teamName;

    @NotEmpty(message = "Owner Name can not be null")
    private String ownerName;

    private String city;

    private String state;

    @Email
    private String emailId;

    private String teamUrl;

}
