package com.example.controller;

import com.example.dto.LoginResponse;
import com.example.dto.LoginRequest;
import com.example.dto.UserRequest;
import com.example.entity.User;
import com.example.exceptions.UserLoginException;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponse> generateToken(@RequestBody LoginRequest loginRequest) throws UserLoginException {

        LoginResponse loginResponse = userService.generateToken(loginRequest);
        return new ResponseEntity<LoginResponse> (loginResponse, HttpStatus.OK);
    }

    @PostMapping ("/sign-up")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) throws UserLoginException {

        if (userService.existsByUsername(userRequest.getUsername())) {
            return new ResponseEntity<String>("Username Already Taken !!!",HttpStatus.BAD_REQUEST);

        } else if (userService.existsByEmail(userRequest.getEmail())) {
            return new ResponseEntity<String>("Email Id Already Taken !!!",HttpStatus.BAD_REQUEST);

        }

            User user = userService.save(userRequest);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);

    }



}
