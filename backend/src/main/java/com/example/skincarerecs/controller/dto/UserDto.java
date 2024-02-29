package com.example.skincarerecs.controller.dto;

import lombok.Data;

import java.util.List;

import jakarta.validation.constraints.*;

@Data
public class UserDto {

    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    private List<TagDto> tags;
}
