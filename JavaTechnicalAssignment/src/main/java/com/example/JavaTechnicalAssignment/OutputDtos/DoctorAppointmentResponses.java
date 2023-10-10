package com.example.JavaTechnicalAssignment.OutputDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAppointmentResponses {
    private String patientName;
    private LocalDateTime appointmentTime;
}
