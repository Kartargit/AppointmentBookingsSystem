package com.example.JavaTechnicalAssignment.InputDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private Integer patientId;
    private Integer doctorId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
