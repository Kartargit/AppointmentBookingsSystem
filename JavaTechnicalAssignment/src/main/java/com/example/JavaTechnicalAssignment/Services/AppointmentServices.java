package com.example.JavaTechnicalAssignment.Services;

import com.example.JavaTechnicalAssignment.CustomExceptions.DoctorNotAvailable;
import com.example.JavaTechnicalAssignment.Entities.Appointment;
import com.example.JavaTechnicalAssignment.Entities.Doctors;
import com.example.JavaTechnicalAssignment.Entities.Patients;
import com.example.JavaTechnicalAssignment.Enums.AppointType;
import com.example.JavaTechnicalAssignment.InputDtos.AppointmentDto;
import com.example.JavaTechnicalAssignment.OutputDtos.DoctorAppointmentResponses;
import com.example.JavaTechnicalAssignment.OutputDtos.PatientAppointmentResponse;
import com.example.JavaTechnicalAssignment.Repositories.AppointmentRepositories;
import com.example.JavaTechnicalAssignment.Repositories.DoctorRepository;
import com.example.JavaTechnicalAssignment.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServices {
    @Autowired
    private AppointmentRepositories appointmentRepositories;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public String bookAppointment(AppointmentDto appointmentDto) throws Exception{
        Appointment appointment = new Appointment();
        appointment.setType(AppointType.PENDING);
        Optional<Patients> optionalPatient = patientRepository.findById(appointmentDto.getPatientId());
        if(!optionalPatient.isPresent()){
            throw new Exception("Patient Id entered is not Correct");
        }

        Optional<Doctors> doctorsOptional = doctorRepository.findById(appointmentDto.getDoctorId());
        if(!doctorsOptional.isPresent()){
            throw new Exception("Doctor Id entered is not Correct");
        }
        Doctors doctor = doctorsOptional.get();
        Patients patient = optionalPatient.get();
        List<Appointment> patientAppointmentList = patient.getAppointmentList();
        List<Appointment> doctorAppointmentList = doctor.getAppointmentList();
        if(patientAppointmentList.size()!=0) {
            if (!checkApplicable(patientAppointmentList,appointmentDto.getStartTime(),appointmentDto.getEndTime())) {
                throw new Exception("Patient already have an appointment in this slot");
            }
        }
        if(doctorAppointmentList.size()!=0) {
            if (!checkApplicable(doctorAppointmentList,appointmentDto.getStartTime(),appointmentDto.getEndTime())) {
                throw new DoctorNotAvailable("Doctor already have an appointment in this slot");
            }
        }
        appointment.setAppointmentStartTime(appointmentDto.getStartTime());
        appointment.setAppointmentEndTime(appointmentDto.getEndTime());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setType(AppointType.BOOKED);
        Appointment newAppointment = appointmentRepositories.save(appointment);

        patientAppointmentList.add(newAppointment);
        doctorAppointmentList.add(newAppointment);

        patientRepository.save(patient);
        doctorRepository.save(doctor);

        return "Appointment has been made successfully";
    }
    public Boolean checkApplicable(List<Appointment> appointmentList, LocalDateTime start,LocalDateTime end){
        for(Appointment appointment:appointmentList){
            if(appointment.getAppointmentStartTime().isEqual(start)||appointment.getAppointmentEndTime().isEqual(end)){
                return false;
            }
            else if(appointment.getAppointmentStartTime().isAfter(start)&&appointment.getAppointmentStartTime().isBefore(end)){
                return false;
            }
            else if(appointment.getAppointmentEndTime().isAfter(start)&&appointment.getAppointmentEndTime().isAfter(end)){
                return false;
            }
        }
        return true;
    }
    public List<PatientAppointmentResponse> patientAppointment(Integer id)throws Exception{
        Optional<Patients> patientsOptional = patientRepository.findById(id);
        if(!patientsOptional.isPresent()){
            throw new Exception("Patient Id entered is not Correct");
        }
        Patients patient = patientsOptional.get();
        List<Appointment> patientAppointmentList = patient.getAppointmentList();
        if(patientAppointmentList.size()==0){
            throw new Exception("Patient has not booked any appointment");
        }
        List<PatientAppointmentResponse> patientAppointmentResponses = new ArrayList<>();
        for(Appointment appointment:patientAppointmentList){
            PatientAppointmentResponse appointmentResponse = PatientAppointmentResponse.builder()
                    .doctorName(appointment.getDoctor().getName())
                    .appointmentTime(appointment.getAppointmentStartTime()).build();
            patientAppointmentResponses.add(appointmentResponse);
        }
        return patientAppointmentResponses;
    }
    public List<DoctorAppointmentResponses> doctorAppointmentResponsesList(Integer id)throws Exception{
        Optional<Doctors> optionalDoctors = doctorRepository.findById(id);
        if(!optionalDoctors.isPresent()){
            throw new Exception("Doctor Id entered is not Correct");
        }
        Doctors doctor = optionalDoctors.get();
        List<Appointment> doctorAppointmentList = doctor.getAppointmentList();
        if(doctorAppointmentList.size()==0){
            throw new Exception("Doctor has no appointment");
        }

        List<DoctorAppointmentResponses> appointmentResponsesList = new ArrayList<>();
        for(Appointment appointment: doctorAppointmentList){
            DoctorAppointmentResponses appointmentResponse = DoctorAppointmentResponses.builder()
                    .patientName(appointment.getPatient().getName())
                    .appointmentTime(appointment.getAppointmentStartTime()).build();
            appointmentResponsesList.add(appointmentResponse);
        }
        return appointmentResponsesList;
    }
}
