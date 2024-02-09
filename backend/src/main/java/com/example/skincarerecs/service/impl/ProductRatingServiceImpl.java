package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.controller.dto.AddProductRatingDto;
import com.example.skincarerecs.controller.dto.ProductRatingDto;
import com.example.skincarerecs.entity.Product;
import com.example.skincarerecs.entity.ProductRating;
import com.example.skincarerecs.entity.User;
import com.example.skincarerecs.mapper.ProductRatingMapper;
import com.example.skincarerecs.repository.ProductRatingRepository;
import com.example.skincarerecs.repository.ProductRepository;
import com.example.skincarerecs.repository.UserRepository;
import com.example.skincarerecs.service.ProductRatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductRatingServiceImpl implements ProductRatingService {

    private final ProductRatingRepository productRatingRepository;
    private final ProductRatingMapper productRatingMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public ProductRatingDto addProductRating(Long productId, AddProductRatingDto productRating) {
        log.info("Adding a new product rating for product ID {}: {}", productId, productRating);

        Product productEntity = productRepository.findById(productId).orElseThrow();
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User userEntity = userRepository.findByEmail(currentUserEmail).orElseThrow();
        ProductRating productRatingEntity = productRatingMapper.mapToProductRating(productRating);

        productRatingEntity.setProduct(productEntity);
        productRatingEntity.setUser(userEntity);

        productRatingRepository.save(productRatingEntity);

        log.info("Product rating added successfully for product ID {}: {}", productId, productRating);

        return productRatingMapper.mapToProductRatingResource(productRatingEntity);
    }

    @Override
    public List<ProductRatingDto> getAllProductRatings(Long productId) {
        log.info("Fetching all product ratings for product ID: {}", productId);
        return productRatingMapper.mapToProductRatingResourceList(productRatingRepository.findAllByProductId(productId));
    }

    @Override
    public ProductRatingDto getProductRatingById(Long productId, Long id) {
        log.info("Fetching product rating by ID {} for product ID: {}", id, productId);
        return productRatingMapper.mapToProductRatingResource(productRatingRepository.findById(id).orElseThrow());
    }

    @Override
    public void deleteProductRating(Long productId, Long id) {
        log.info("Deleting product rating with ID {} for product ID: {}", id, productId);
        productRatingRepository.deleteById(id);
        log.info("Product rating deleted successfully with ID {} for product ID: {}", id, productId);
    }
}
