package com.example.skincarerecs.controller.resources;

import lombok.Data;

@Data
public class ProductRatingResource {
    private Long id;
    private String comment;
    private int rating;
    private String product_name;
    private String username;
}
