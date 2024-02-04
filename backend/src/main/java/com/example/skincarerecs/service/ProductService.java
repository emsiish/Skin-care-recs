package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.dto.ProductDto;
import com.example.skincarerecs.controller.dto.ProductRatingHelperDto;
import com.example.skincarerecs.controller.dto.TagDto;

import java.util.List;

public interface ProductService {
    ProductDto addProduct(ProductDto product);
    ProductDto getProductById(Long id);
    ProductDto updateProduct(Long id, ProductDto product);
    void deleteProduct(Long id);

    List<ProductDto> getAllProducts();

    List<ProductRatingHelperDto> getProductsByTags(List<TagDto> tags);
}
