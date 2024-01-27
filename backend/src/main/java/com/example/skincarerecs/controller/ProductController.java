package com.example.skincarerecs.controller;

import com.example.skincarerecs.controller.resources.ProductResource;
import com.example.skincarerecs.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResource addProduct(@RequestBody ProductResource product) {
        return productService.addProduct(product);
    }

    @GetMapping(path = "/{id}")
    public ProductResource getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping(path = "/{id}")
    public ProductResource updateProduct(@PathVariable Long id, @RequestBody ProductResource product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
