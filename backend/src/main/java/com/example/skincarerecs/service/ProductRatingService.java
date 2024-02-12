package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.dto.AddProductRatingDto;
import com.example.skincarerecs.controller.dto.ProductRatingDto;

import java.util.List;

public interface ProductRatingService {
    ProductRatingDto addProductRating(Long productId, AddProductRatingDto productRating);
    List<ProductRatingDto> getAllProductRatings(Long productId);
    ProductRatingDto getProductRatingById(Long productId, Long id);
    void deleteProductRating(Long productId, Long id);
}
