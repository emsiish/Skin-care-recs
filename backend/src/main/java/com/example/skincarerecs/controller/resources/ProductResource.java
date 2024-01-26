package com.example.skincarerecs.controller.resources;

import com.example.skincarerecs.entity.Tag;
import lombok.Data;

import java.util.List;

@Data
public class ProductResource {
    private Long id;
    private String name;
    private String brand;
    private String type;
    private String image;
    private String price;

    private List<String> tags_names;

}
