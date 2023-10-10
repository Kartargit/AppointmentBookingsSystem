package com.example.JavaTechnicalAssignment.OutputDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuggestDoctorResponse {
    private String name;
    private String phoneNumber;
    private String emailId;

}
