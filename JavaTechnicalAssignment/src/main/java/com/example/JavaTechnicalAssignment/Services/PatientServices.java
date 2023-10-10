package com.example.JavaTechnicalAssignment.Services;

import com.example.JavaTechnicalAssignment.Entities.Patients;
import com.example.JavaTechnicalAssignment.InputDtos.AddPatientDto;
import com.example.JavaTechnicalAssignment.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientServices {
    @Autowired
    private PatientRepository patientRepository;

    public String addPatient(AddPatientDto patientDto)throws Exception{
        if(patientDto.getName().length()<3){
            throw new Exception("Name should be of minimum 3 character long");
        }
        if(patientDto.getCity().length()>20){
            throw new Exception("City should be of maximum 20 character long");
        }
        Patients patient = Patients.builder()
                .city(patientDto.getCity())
                .emailId(patientDto.getEmailId())
                .name(patientDto.getName())
                .phoneNumber(patientDto.getPhoneNumber())
                .symptoms(patientDto.getSymptoms())
                .build();
        patientRepository.save(patient);
        return "Patient has been added to database Successfully";
    }
    public String removePatient(Integer id)throws Exception{
        Optional<Patients> optional = patientRepository.findById(id);
        if(!optional.isPresent()){
            throw new Exception("Entered Id is not correct");
        }
        patientRepository.deleteById(id);
        return "Patient with Id "+id+" has been removed";
    }
}
