package com.example.JavaTechnicalAssignment.Advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
//import java.util.Map;

@RestControllerAdvice
public class argsExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HashMap<String,String> handleInvalidArguments(MethodArgumentNotValidException ex){
        HashMap<String,String> exceptionMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            exceptionMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return exceptionMap;
    }
}
