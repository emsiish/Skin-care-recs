package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.controller.resources.ProductRatingResource;
import com.example.skincarerecs.entity.ProductRating;
import com.example.skincarerecs.repository.ProductRatingRepository;
import com.example.skincarerecs.service.ProductRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.skincarerecs.mapper.ProductRatingMapper.PRODUCT_RATING_MAPPER;

@Service
@RequiredArgsConstructor
public class ProductRatingServiceImpl implements ProductRatingService {

    private final ProductRatingRepository productRatingRepository;

    @Override
    public ProductRatingResource addProductRating(Long productId, ProductRatingResource productRating) {
        ProductRating productRatingEntity = PRODUCT_RATING_MAPPER.mapToProductRating(productRating);

        productRatingRepository.save(productRatingEntity);

        return PRODUCT_RATING_MAPPER.mapToProductRatingResource(productRatingEntity);
    }

    @Override
    public List<ProductRatingResource> getAllProductRatings(Long productId) {
        return PRODUCT_RATING_MAPPER.mapToProductRatingResourceList(productRatingRepository.findAll());
    }

    @Override
    public ProductRatingResource getProductRatingById(Long productId, Long id) {
        return PRODUCT_RATING_MAPPER.mapToProductRatingResource(productRatingRepository.findById(id).orElseThrow());
    }

    @Override
    public ProductRatingResource updateProductRating(Long productId, Long id, ProductRatingResource productRating) {
        return null;
    }

    @Override
    public void deleteProductRating(Long productId, Long id) {
        productRatingRepository.deleteById(id);
    }
}
