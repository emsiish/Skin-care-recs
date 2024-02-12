package com.example.skincarerecs.controller.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class TagDto {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;
}


