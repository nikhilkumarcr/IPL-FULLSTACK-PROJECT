package com.example.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GobalExceptionHandler {


    @ExceptionHandler(ExceptionErrorHandler.class)
    public ResponseEntity<String> handleException(ExceptionErrorHandler exceptionHandler){

        return  new ResponseEntity<String>("Input field is Empty !!!", HttpStatus.BAD_REQUEST);
    }
}
