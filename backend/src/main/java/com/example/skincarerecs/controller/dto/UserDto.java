package com.example.skincarerecs.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String password;

    private List<TagDto> tags;
}
