package com.example.skincarerecs.controller.dto;

import lombok.Data;

@Data
public class DoctorRatingDto {
    private Long id;
    private String comment;
    private int rating;
    private UserDto username;
}
