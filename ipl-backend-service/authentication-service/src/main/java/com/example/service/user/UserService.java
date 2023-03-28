package com.example.service.user;

import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;
import com.example.dto.UserRequest;
import com.example.entity.User;
import com.example.exceptions.UserLoginException;


public interface UserService {
    User save(UserRequest user) throws UserLoginException;

    LoginResponse generateToken(LoginRequest loginRequest) throws UserLoginException;
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
