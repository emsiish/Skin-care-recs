package com.example.skincarerecs.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String brand;
    private String type;
    private String image;
    private String price;

    private List<TagDto> tags_names;

}
