package com.example.dto;

import com.example.entity.User;
import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String email;

    public User getUserFromUserRequest(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        return user;
    }

}
