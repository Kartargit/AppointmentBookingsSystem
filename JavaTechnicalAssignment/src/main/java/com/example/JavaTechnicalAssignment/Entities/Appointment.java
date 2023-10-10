package com.example.JavaTechnicalAssignment.Entities;

import com.example.JavaTechnicalAssignment.Enums.AppointType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

    private LocalDateTime appointmentStartTime;
    private LocalDateTime appointmentEndTime;

    @Enumerated(value = EnumType.STRING)
    private AppointType type ;

    @ManyToOne
    @JoinColumn
    private Doctors doctor;

    @ManyToOne
    @JoinColumn
    private Patients patient;

}
