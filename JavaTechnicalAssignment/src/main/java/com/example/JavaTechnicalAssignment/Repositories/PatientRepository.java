package com.example.JavaTechnicalAssignment.Repositories;

import com.example.JavaTechnicalAssignment.Entities.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patients,Integer> {
}
