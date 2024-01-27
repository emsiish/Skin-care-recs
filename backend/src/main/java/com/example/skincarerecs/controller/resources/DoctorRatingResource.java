package com.example.skincarerecs.controller.resources;

import lombok.Data;

@Data
public class DoctorRatingResource {
    private Long id;
    private String comment;
    private int rating;
    private String username;
}
