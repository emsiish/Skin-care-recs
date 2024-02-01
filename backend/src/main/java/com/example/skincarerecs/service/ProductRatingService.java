package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.dto.ProductRatingDto;

import java.util.List;

public interface ProductRatingService {
    ProductRatingDto addProductRating(Long productId, ProductRatingDto productRating);
    List<ProductRatingDto> getAllProductRatings(Long productId);
    ProductRatingDto getProductRatingById(Long productId, Long id);

    //?? do i need it?
    ProductRatingDto updateProductRating(Long productId, Long id, ProductRatingDto productRating);
    void deleteProductRating(Long productId, Long id);
}
