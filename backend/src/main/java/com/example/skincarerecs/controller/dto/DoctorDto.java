package com.example.skincarerecs.controller.dto;

import lombok.Data;

@Data
public class DoctorDto {
    private Long id;
    private String name;
    private String telephone;
    private String email;
    private String hospital;
}
