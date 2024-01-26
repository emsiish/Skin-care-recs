package com.example.skincarerecs.controller.resources;

import lombok.Data;

import java.util.List;

@Data
public class UserResource {
    private Long id;
    private String username;
    private String password;

    private List<String> tags_names;
}
