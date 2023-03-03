package com.example.service.user;

import com.example.dto.UserRequest;
import com.example.entity.User;


public interface UserService {
    User save(UserRequest user);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
