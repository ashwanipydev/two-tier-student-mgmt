package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDto {
    private Integer studentId;

    @NotBlank
    private String firstName;

    private String lastName;

    @Email
    private String email;

    private String phone;
    private LocalDate dob;

    // getters/setters
}