package com.example.skincarerecs.controller;

import com.example.skincarerecs.controller.dto.ProductDto;
import com.example.skincarerecs.controller.dto.ProductRatingSummaryDto;
import com.example.skincarerecs.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductDto addProduct(@Valid @RequestBody ProductDto product) {
        return productService.addProduct(product);
    }

    @GetMapping(path = "/getByUserTags")
    public List<ProductRatingSummaryDto> getProductsByTags() {
        return productService.getProductsByTags();
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(path = "/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping(path = "/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
