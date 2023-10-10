package com.example.JavaTechnicalAssignment.Controllers;

import com.example.JavaTechnicalAssignment.InputDtos.AppointmentDto;
import com.example.JavaTechnicalAssignment.OutputDtos.DoctorAppointmentResponses;
import com.example.JavaTechnicalAssignment.OutputDtos.PatientAppointmentResponse;
import com.example.JavaTechnicalAssignment.Services.AppointmentServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentServices appointmentServices;

    @PostMapping("/booking")
    public ResponseEntity appointBooking(@RequestBody AppointmentDto appointmentDto){
        try {
            String res = appointmentServices.bookAppointment(appointmentDto);
            return new ResponseEntity(res, HttpStatus.CREATED);
        }
        catch (Exception e){
            log.error("Appointment not booked due to {}",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/listOfDoctor")
    public ResponseEntity getListOfAppointedDoctor(@RequestParam("Id") Integer id){
        try{
            List<PatientAppointmentResponse> patientAppointmentResponses = appointmentServices.patientAppointment(id);
            return new ResponseEntity(patientAppointmentResponses, HttpStatus.FOUND);
        }
        catch (Exception e){
            log.error("Not Success {}",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/listOfPatient")
    public ResponseEntity getListOfAppointedPatient(@RequestParam("Id") Integer id){
        try{
            List<DoctorAppointmentResponses> doctorAppointmentResponses = appointmentServices.doctorAppointmentResponsesList(id);
            return new ResponseEntity(doctorAppointmentResponses, HttpStatus.FOUND);
        }
        catch (Exception e){
            log.error("Not Success {}",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
