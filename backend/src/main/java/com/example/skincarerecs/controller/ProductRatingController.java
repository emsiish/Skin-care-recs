package com.example.skincarerecs.controller;

import com.example.skincarerecs.controller.resources.ProductRatingResource;
import com.example.skincarerecs.service.ProductRatingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/products/{productId}/ratings")
public class ProductRatingController {

    private final ProductRatingService productRatingService;

    @PostMapping
    public ProductRatingResource addProductRating(@PathVariable Long productId, @RequestBody ProductRatingResource productRating) {
        return productRatingService.addProductRating(productId, productRating);
    }

    @GetMapping
    public List<ProductRatingResource> getAllProductRatings(@PathVariable Long productId) {
        return productRatingService.getAllProductRatings(productId);
    }
    @GetMapping(path = "/{id}")
    public ProductRatingResource getProductRatingById(@PathVariable Long productId, @PathVariable Long id) {
        return productRatingService.getProductRatingById(productId, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProductRating(@PathVariable Long productId, @PathVariable Long id) {
        productRatingService.deleteProductRating(productId, id);
    }
}
