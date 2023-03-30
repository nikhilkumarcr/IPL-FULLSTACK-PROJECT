package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<String> handleTeamNotFoundError(TeamNotFoundException ex){

        return  new ResponseEntity<String>(ex.getErrorMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<String> handlePlayerNotFoundError(PlayerNotFoundException ex){

        return  new ResponseEntity<String>(ex.getErrorMessage(), HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex){

        return  new ResponseEntity<String>("No Data present in the given Id !!! Enter valid Id !!!",HttpStatus.NOT_FOUND);
    }

}
