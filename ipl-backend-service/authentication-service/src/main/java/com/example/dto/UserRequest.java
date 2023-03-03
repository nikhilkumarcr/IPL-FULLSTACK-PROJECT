package com.example.dto;

import com.example.entity.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserRequest {

    private String username;

    private String password;

    private String email;


}
