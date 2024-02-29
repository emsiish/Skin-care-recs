package com.example.skincarerecs.controller.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DoctorRatingSummaryDto {
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

    private String image;

    @Digits(integer = 1, fraction = 1, message = "Invalid rating format")
    private Double averageRating;

    private int count;
}
