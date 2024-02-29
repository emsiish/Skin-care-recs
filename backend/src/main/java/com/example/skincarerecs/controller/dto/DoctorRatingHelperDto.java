package com.example.skincarerecs.controller.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DoctorRatingHelperDto {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Phone cannot be blank")
    private String telephone;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Hospital cannot be blank")
    private String hospital;

    @Digits(integer = 1, fraction = 1, message = "Invalid rating format")
    private Double averageRating;
    private int count;
}
