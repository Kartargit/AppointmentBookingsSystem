package com.example.JavaTechnicalAssignment.Services;

import com.example.JavaTechnicalAssignment.Entities.Doctors;
import com.example.JavaTechnicalAssignment.Entities.Patients;
import com.example.JavaTechnicalAssignment.Enums.City;
import com.example.JavaTechnicalAssignment.Enums.Speciality;
import com.example.JavaTechnicalAssignment.Enums.Symptoms;
import com.example.JavaTechnicalAssignment.InputDtos.AddDoctorDto;
import com.example.JavaTechnicalAssignment.OutputDtos.SuggestDoctorResponse;
import com.example.JavaTechnicalAssignment.Repositories.DoctorRepository;
import com.example.JavaTechnicalAssignment.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServices {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    public String addDoctor(AddDoctorDto addDoctorDto)throws Exception{
        if(addDoctorDto.getName().length()<3){
            throw new Exception("Name should be of minimum 3 character long");
        }
        Doctors doctor = Doctors.builder().name(addDoctorDto.getName())
                .emailId(addDoctorDto.getEmailId())
                .city(addDoctorDto.getCity())
                .phoneNumber(addDoctorDto.getPhoneNumber())
                .speciality(addDoctorDto.getSpeciality())
                .isAvailable(true).build();
        doctorRepository.save(doctor);
        return "Doctor has been added to the database successfully";
    }
    public String removeDoctor(Integer id)throws Exception{
        Optional<Doctors> optional = doctorRepository.findById(id);
        if(!optional.isPresent()){
            throw new Exception("Incorrect Doctor id");
        }
        Doctors doctor = optional.get();
        doctorRepository.delete(doctor);
        return "Doctor has been removed successfully";
    }
    public List<SuggestDoctorResponse> getDoctors(Integer id)throws Exception{
        Optional<Patients> optionalPatients = patientRepository.findById(id);
        if(!optionalPatients.isPresent()){
            throw  new Exception("Patient id is not correct");
        }
        Patients patient = optionalPatients.get();
        String city = patient.getCity();
        City location = null;
        if(city.equals(City.Delhi.toString())){
            location = City.Delhi;
        } else if (city.equals(City.Noida.toString())) {
            location = City.Noida;
        } else if (city.equals(City.Faridabad.toString())) {
            location = City.Faridabad;
        }
        else {
            throw new Exception("We are still waiting to expand to your location");
        }
        Speciality speciality = getSpecialityFromSymptoms(patient.getSymptoms());
        if(speciality==null){
            throw new Exception("There isn’t any doctor present at your location for your symptom");
        }
        List<SuggestDoctorResponse> suggestedDoctors = findDoctorForPatient(location, speciality);
        if(suggestedDoctors.size()==0){
            throw new Exception("There isn’t any doctor present at your location for your symptom");
        }
        return suggestedDoctors;
    }
    public Speciality getSpecialityFromSymptoms(Symptoms symptoms){
        if(symptoms.equals(Symptoms.Arthritis)||symptoms.equals(Symptoms.Back_Pain)||symptoms.equals(Symptoms.Tissue_injuries)){
            return Speciality.Orthopedic;
        } else if (symptoms.equals(Symptoms.Dysmenorrhea)) {
            return Speciality.Gynecology;
        } else if (symptoms.equals(Symptoms.Ear_pain)) {
            return Speciality.ENT;
        } else if (symptoms.equals(Symptoms.Skin_Burn)||symptoms.equals(Symptoms.Skin_Infection)) {
            return Speciality.Dermatology;
        }
        return null;
    }
    public List<SuggestDoctorResponse> findDoctorForPatient(City city,Speciality speciality){
        List<Doctors> doctorsList = doctorRepository.findDoctorsByCityAndSpeciality(city,speciality);
        if(doctorsList==null){
            return null;
        }
        List<SuggestDoctorResponse> doctorResponses = new ArrayList<>();
        for(Doctors doctor : doctorsList){
            SuggestDoctorResponse doctorResponse = SuggestDoctorResponse.builder()
                    .name(doctor.getName()).phoneNumber(doctor.getPhoneNumber())
                    .emailId(doctor.getEmailId()).build();
            doctorResponses.add(doctorResponse);
        }

        return doctorResponses;
    }
}
