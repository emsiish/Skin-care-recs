package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.dto.ProductDto;
import com.example.skincarerecs.controller.dto.ProductRatingSummaryDto;

import java.util.List;

public interface ProductService {
    ProductDto addProduct(ProductDto product);
    ProductDto getProductById(Long id);
    ProductDto updateProduct(Long id, ProductDto product);
    void deleteProduct(Long id);

    List<ProductDto> getAllProducts();

    List<ProductRatingSummaryDto> getProductsByTags();
}
