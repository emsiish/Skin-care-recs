package com.example.skincarerecs.controller.resources;

import lombok.Data;

@Data
public class DoctorResource {
    private Long id;
    private String name;
    private String telephone;
    private String email;
    private String hospital;
}
