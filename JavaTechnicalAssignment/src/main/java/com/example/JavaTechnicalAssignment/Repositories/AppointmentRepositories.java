package com.example.JavaTechnicalAssignment.Repositories;

import com.example.JavaTechnicalAssignment.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepositories extends JpaRepository<Appointment,Integer> {

}
