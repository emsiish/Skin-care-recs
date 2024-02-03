package com.example.skincarerecs.controller.dto;

import lombok.Data;

import java.util.List;

import jakarta.validation.constraints.*;

@Data
public class ProductDto {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Brand cannot be blank")
    private String brand;

    @NotBlank(message = "Type cannot be blank")
    private String type;

    @NotBlank(message = "Image cannot be blank")
    private String image;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Digits(integer = 6, fraction = 2, message = "Invalid price format")
    private Double price;

    private List<TagDto> tags_names;

}
