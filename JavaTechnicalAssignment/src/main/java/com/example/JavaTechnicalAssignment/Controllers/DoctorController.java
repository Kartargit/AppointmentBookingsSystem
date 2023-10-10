package com.example.JavaTechnicalAssignment.Controllers;

import com.example.JavaTechnicalAssignment.InputDtos.AddDoctorDto;
import com.example.JavaTechnicalAssignment.OutputDtos.SuggestDoctorResponse;
import com.example.JavaTechnicalAssignment.Services.DoctorServices;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorServices doctorServices;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody @Valid AddDoctorDto addDoctorDto) {
        try {
            return new ResponseEntity(doctorServices.addDoctor(addDoctorDto), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Not added {}", e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
    @PutMapping("/remove")
    public ResponseEntity removeDoctor(@RequestParam("id") Integer id){
        try {
            String res = doctorServices.removeDoctor(id);
            return new ResponseEntity(res,HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Failed to remove {}",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/suggestDoctors")
    public ResponseEntity getDoctorListForPatient(@RequestParam("id") Integer id){
        try {
            List<SuggestDoctorResponse> doctorList = doctorServices.getDoctors(id);
            return new ResponseEntity(doctorList,HttpStatus.FOUND);
        }
        catch (Exception e){
            log.error("Not Found {}",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
}
