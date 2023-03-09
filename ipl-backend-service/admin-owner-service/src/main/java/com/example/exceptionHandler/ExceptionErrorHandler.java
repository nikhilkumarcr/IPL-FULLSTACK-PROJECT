package com.example.exceptionHandler;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionErrorHandler extends  Exception{

    private String errorMessage;


}
