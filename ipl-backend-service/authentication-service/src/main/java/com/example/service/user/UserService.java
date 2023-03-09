package com.example.service.user;

import com.example.dto.UserRequest;
import com.example.entity.User;
import com.example.exceptionHandler.ExceptionErrorHandler;


public interface UserService {
    User save(UserRequest user) throws ExceptionErrorHandler;
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
