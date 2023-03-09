package com.example.errors;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionErrorHandler extends  RuntimeException{

    private String errorMessage;


}
