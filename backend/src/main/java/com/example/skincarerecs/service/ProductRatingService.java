package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.resources.ProductRatingResource;

import java.util.List;

public interface ProductRatingService {
    ProductRatingResource addProductRating(Long productId, ProductRatingResource productRating);
    List<ProductRatingResource> getAllProductRatings(Long productId);
    ProductRatingResource getProductRatingById(Long productId, Long id);

    //?? do i need it?
    ProductRatingResource updateProductRating(Long productId, Long id, ProductRatingResource productRating);
    void deleteProductRating(Long productId, Long id);
}
