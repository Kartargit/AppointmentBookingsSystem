package com.example.JavaTechnicalAssignment.Entities;

import com.example.JavaTechnicalAssignment.Enums.Symptoms;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    private String name;
    @Email
    private String emailId;
    private String phoneNumber;
    private String city;

    @Enumerated(value = EnumType.STRING)
    private Symptoms symptoms;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<Appointment> appointmentList = new ArrayList<>();
}
