package com.example.skincarerecs.controller.dto;

import lombok.Data;

@Data
public class ProductRatingDto {
    private Long id;
    private String comment;
    private int rating;
    private String product_name;
    private UserDto username;
}
