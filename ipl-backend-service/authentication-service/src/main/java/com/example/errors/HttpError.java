package com.example.errors;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class HttpError {
    private HttpStatus code;
    private String message;
}
