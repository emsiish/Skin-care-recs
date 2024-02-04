package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.controller.dto.ProductDto;
import com.example.skincarerecs.controller.dto.ProductRatingHelperDto;
import com.example.skincarerecs.controller.dto.TagDto;
import com.example.skincarerecs.entity.Product;
import com.example.skincarerecs.entity.Tag;
import com.example.skincarerecs.mapper.ProductMapper;
import com.example.skincarerecs.repository.ProductRatingRepository;
import com.example.skincarerecs.repository.ProductRepository;
import com.example.skincarerecs.repository.TagRepository;
import com.example.skincarerecs.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductRatingRepository productRatingRepository;
    private final ProductMapper productMapper;
    private final TagRepository tagRepository;

    @Override
    public ProductDto addProduct(ProductDto product) {
        log.info("Adding a new product: {}", product);
        Product productEntity = productMapper.mapToProduct(product);
        List<Tag> tagsEntity = product.getTags().stream()
                .map(tag -> tagRepository.findByName(tag.getName()).orElseThrow())
                .collect(Collectors.toCollection(ArrayList::new));

        productEntity.setTags(tagsEntity);
        productRepository.save(productEntity);
        log.info("Product added successfully: {}", product);
        return productMapper.mapToProductResource(productEntity);
    }

    @Override
    public ProductDto getProductById(Long id) {
        log.info("Fetching product by ID: {}", id);
        return productMapper.mapToProductResource(productRepository.findById(id).orElseThrow());
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto product) {
        log.info("Updating product with ID {}: {}", id, product);
        Product productEntity = productRepository.findById(id).orElseThrow();
        productEntity.setName(product.getName());
        productEntity.setBrand(product.getBrand());
        productEntity.setType(product.getType());
        productEntity.setImage(product.getImage());
        productEntity.setPrice(product.getPrice());

        productRepository.save(productEntity);

        log.info("Product updated successfully: {}", productEntity);

        return productMapper.mapToProductResource(productEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product with ID: {}", id);
        productRepository.deleteById(id);
        log.info("Product deleted successfully with ID: {}", id);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        log.info("Fetching all products.");
        return productMapper.mapToProductResourceList(productRepository.findAll());
    }

    //one product should include at least one tag
    @Override
    public List<ProductRatingHelperDto> getProductsByTags(List<TagDto> tags) {
        log.info("Fetching products by tags: {}", tags);

        List<String> tagNames = tags.stream().map(TagDto::getName).collect(Collectors.toList());

        List<Product> products = productRepository.findByTags(tagNames);

        List<ProductRatingHelperDto> productRatingsHelper = productMapper.mapToProductRatingHelperResourceList(products);

        productRatingsHelper.forEach(productRatingHelper -> {
            productRatingHelper.setAverageRating(productRatingRepository.getAverageRatingByProductId(productRatingHelper.getId()));
            productRatingHelper.setCount(productRatingRepository.countByProductId(productRatingHelper.getId()));
        });

        return productRatingsHelper;
    }

}
