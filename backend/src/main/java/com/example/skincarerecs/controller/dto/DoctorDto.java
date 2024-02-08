package com.example.skincarerecs.controller.dto;

import lombok.Data;

import jakarta.validation.constraints.*;
@Data
public class DoctorDto {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Phone cannot be blank")
    private String phoneNumber;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Hospital cannot be blank")
    private String hospital;
}
