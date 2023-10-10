package com.example.JavaTechnicalAssignment.InputDtos;

import com.example.JavaTechnicalAssignment.Enums.Symptoms;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
//import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonComponent
public class AddPatientDto {
    @NotNull(message = "Invalid Patient Name")
    private String name;

    @Email(message = "Please enter a valid email Id")
    private String emailId;

    @Pattern(regexp = "^\\d{10}$",message = "Invalid Phone Number entered")
    private String phoneNumber;

    @NotNull(message = "Please enter city name")
    private String city;
    @Enumerated(value = EnumType.STRING)
    private Symptoms symptoms;

}
