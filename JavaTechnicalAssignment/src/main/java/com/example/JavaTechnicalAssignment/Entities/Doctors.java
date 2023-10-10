package com.example.JavaTechnicalAssignment.Entities;

import com.example.JavaTechnicalAssignment.Enums.City;
import com.example.JavaTechnicalAssignment.Enums.Speciality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorId;

    private String name;
    private String emailId;
    private String phoneNumber;
    private Boolean isAvailable;
    @Enumerated(value = EnumType.STRING)
    private City city;
    @Enumerated(value = EnumType.STRING)
    private Speciality speciality;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<Appointment> appointmentList = new ArrayList<>();
}
