package com.example.exceptions;

public class UserExitException extends RuntimeException{

    public UserExitException(String message){
        super(message);
    }

}
