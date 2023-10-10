package com.example.JavaTechnicalAssignment.CustomExceptions;

public class DoctorNotAvailable extends Exception{
    public DoctorNotAvailable(String message){
        super(message);
    }
}
