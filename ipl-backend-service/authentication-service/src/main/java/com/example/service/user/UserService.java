package com.example.service.user;

import com.example.dto.UserRequest;
import com.example.entity.User;

import java.util.List;

public interface UserService {
    User save(UserRequest user);
//    List<User> findAll();
//    User findOne(String username);
   Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
