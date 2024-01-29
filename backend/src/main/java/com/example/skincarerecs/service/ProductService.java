package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.resources.ProductResource;

import java.util.List;

public interface ProductService {
    ProductResource addProduct(ProductResource product);
    ProductResource getProductById(Long id);
    ProductResource updateProduct(Long id, ProductResource product);
    void deleteProduct(Long id);

    List<ProductResource> getAllProducts();
}
