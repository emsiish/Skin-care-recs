package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.controller.dto.ProductRatingDto;
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
    public ProductRatingDto addProductRating(Long productId, ProductRatingDto productRating) {
        ProductRating productRatingEntity = PRODUCT_RATING_MAPPER.mapToProductRating(productRating);

        productRatingRepository.save(productRatingEntity);

        return PRODUCT_RATING_MAPPER.mapToProductRatingResource(productRatingEntity);
    }

    @Override
    public List<ProductRatingDto> getAllProductRatings(Long productId) {
        return PRODUCT_RATING_MAPPER.mapToProductRatingResourceList(productRatingRepository.findAll());
    }

    @Override
    public ProductRatingDto getProductRatingById(Long productId, Long id) {
        return PRODUCT_RATING_MAPPER.mapToProductRatingResource(productRatingRepository.findById(id).orElseThrow());
    }

    @Override
    public ProductRatingDto updateProductRating(Long productId, Long id, ProductRatingDto productRating) {
        return null;
    }

    @Override
    public void deleteProductRating(Long productId, Long id) {
        productRatingRepository.deleteById(id);
    }
}
