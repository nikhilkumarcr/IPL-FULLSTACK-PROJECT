package com.example.controller;

import com.example.dto.LoginResponse;
import com.example.dto.LoginRequest;
import com.example.dto.UserRequest;
import com.example.entity.User;
import com.example.errors.ExceptionErrorHandler;
import com.example.security.jwt.JwtUtils;
import com.example.security.services.UserDetailsImpl;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
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


    @PostMapping("/sign-in")
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest){

        if(loginRequest.getUsername().isEmpty() || loginRequest.getUsername().length()==0){

            return new ResponseEntity<String>("User name can not be empty !!! Enter a valid username",HttpStatus.BAD_REQUEST);
        }else if(loginRequest.getPassword().isEmpty() || loginRequest.getPassword().length()==0){

            return new ResponseEntity<String>("Password can not be empty !!! Enter a valid password",HttpStatus.BAD_REQUEST);
        }

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    ));


            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateJwtToken(authentication);

            UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
            List<String> role = user.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUsername(loginRequest.getUsername());
            loginResponse.setToken(token);
            loginResponse.setRoles(role);

            return ResponseEntity.status(HttpStatus.OK).body(loginResponse);

        }catch (ExceptionErrorHandler e){
            ExceptionErrorHandler ex = new ExceptionErrorHandler(e.getErrorCode(),e.getErrorMessage());
            return  new ResponseEntity<ExceptionErrorHandler>(ex,HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            ExceptionErrorHandler ex = new ExceptionErrorHandler("811","Error in given log-in details !!! Check log-in details "+e.getMessage());
            return  new ResponseEntity<ExceptionErrorHandler>(ex,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping ("/sign-up")
    public ResponseEntity<?> saveUser(@RequestBody   UserRequest userRequest) {

        if (userService.existsByUsername(userRequest.getUsername())) {
            return new ResponseEntity<String>("Username Already Taken !!!",HttpStatus.BAD_REQUEST);

        } else if (userService.existsByEmail(userRequest.getEmail())) {
            return new ResponseEntity<String>("Email Id Already Taken !!!",HttpStatus.BAD_REQUEST);

        }
        try {
            User user = userService.save(userRequest);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }catch (ExceptionErrorHandler e){
            ExceptionErrorHandler ex = new ExceptionErrorHandler(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ExceptionErrorHandler>(ex,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ExceptionErrorHandler ex = new ExceptionErrorHandler("811", "Error in Auth Controller at sign-up !!!" + e.getMessage());
            return new ResponseEntity<ExceptionErrorHandler>(ex,HttpStatus.BAD_REQUEST);
        }


    }



}
