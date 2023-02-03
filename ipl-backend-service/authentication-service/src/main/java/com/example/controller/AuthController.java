package com.example.controller;

import com.example.dto.LoginResponse;
import com.example.dto.LoginRequest;
import com.example.dto.UserRequest;
import com.example.entity.User;
import com.example.errors.HttpError;
import com.example.repository.UserRepository;
import com.example.security.jwt.JwtUtils;
import com.example.security.services.UserDetailsImpl;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtUtils jwtTokenUtil;

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserRepository userRepository;

    @PostMapping("/sign-in")
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest) throws AuthenticationException {

        Authentication authentication=null;

        try { authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                ));
        }catch (BadCredentialsException e){
            HttpError httpError = new HttpError();
            httpError.setCode(HttpStatus.UNAUTHORIZED);
            httpError.setMessage(e.getMessage());
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(httpError);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateJwtToken(authentication);

        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        List<String> role = user.getAuthorities().stream()
                .map(item->item.getAuthority())
                .collect(Collectors.toList());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(loginRequest.getUsername());
        loginResponse.setToken(token);
        loginResponse.setRoles(role);

        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @PostMapping ("/sign-up")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username Already Taken!!!");

        } else if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Already Taken!!!");

        } else {
            User user = new User();
            user = userService.save(userRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
    }



}
