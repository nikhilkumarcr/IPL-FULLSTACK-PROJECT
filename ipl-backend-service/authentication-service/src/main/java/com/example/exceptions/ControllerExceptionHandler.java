package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserLoginException.class)
    public ResponseEntity<String> handleUserNotFoundError(UserLoginException ex){

        return  new ResponseEntity<String>(ex.getErrorMessage(), HttpStatus.BAD_REQUEST);

    }



}
