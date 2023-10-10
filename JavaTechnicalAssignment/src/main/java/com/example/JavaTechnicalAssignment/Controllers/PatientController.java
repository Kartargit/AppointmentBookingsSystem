package com.example.JavaTechnicalAssignment.Controllers;

import com.example.JavaTechnicalAssignment.InputDtos.AddPatientDto;
import com.example.JavaTechnicalAssignment.Services.PatientServices;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientServices patientServices;

    @PostMapping("/addPatient")
    public ResponseEntity addPatient(@RequestBody @Valid AddPatientDto patientDto){
        try{
            return new ResponseEntity(patientServices.addPatient(patientDto), HttpStatus.CREATED);
        }
        catch (Exception e){
            log.error("Not added {}",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }

    }
    @PostMapping("/remove")
    public ResponseEntity removePatient(@RequestParam("id")Integer id){
        try {
            String res = patientServices.removePatient(id);
            return new ResponseEntity(res,HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Failed to remove patient {}",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
