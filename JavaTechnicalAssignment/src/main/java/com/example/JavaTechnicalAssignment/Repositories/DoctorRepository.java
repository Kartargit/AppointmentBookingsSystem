package com.example.JavaTechnicalAssignment.Repositories;

import com.example.JavaTechnicalAssignment.Entities.Doctors;
import com.example.JavaTechnicalAssignment.Enums.City;
import com.example.JavaTechnicalAssignment.Enums.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctors,Integer> {
    List<Doctors> findDoctorsByCityAndSpeciality(City city, Speciality speciality);
}
