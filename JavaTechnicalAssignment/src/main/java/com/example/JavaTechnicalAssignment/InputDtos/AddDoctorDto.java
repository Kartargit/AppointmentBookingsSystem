package com.example.JavaTechnicalAssignment.InputDtos;

import com.example.JavaTechnicalAssignment.Enums.City;
import com.example.JavaTechnicalAssignment.Enums.Speciality;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDoctorDto {
    @NotNull(message = "Invalid Patient Name")
    private String name;

    @Email
    private String emailId;

    @Pattern(regexp = "^\\d{10}$",message = "Invalid Phone Number entered")
    private String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private City city;

    @Enumerated(value = EnumType.STRING)
    private Speciality speciality;
}
