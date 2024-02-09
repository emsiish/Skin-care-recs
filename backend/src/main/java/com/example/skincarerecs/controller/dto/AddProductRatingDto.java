package com.example.skincarerecs.controller.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class AddProductRatingDto {
    private Long id;
    private String comment;

    @Min(value = 0, message = "Rating must be greater than or equal to 0")
    @Max(value = 5, message = "Rating must be less than or equal to 5")
    @Digits(integer = 1, fraction = 1, message = "Invalid rating format")
    private Double rating;
}
