package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.controller.resources.ProductResource;
import com.example.skincarerecs.entity.Product;
import com.example.skincarerecs.repository.ProductRepository;
import com.example.skincarerecs.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.skincarerecs.mapper.ProductMapper.PRODUCT_MAPPER;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public ProductResource addProduct(ProductResource product) {
        Product productEntity = PRODUCT_MAPPER.mapToProduct(product);
        productRepository.save(productEntity);
        return PRODUCT_MAPPER.mapToProductResource(productEntity);
    }

    @Override
    public ProductResource getProductById(Long id) {
        return PRODUCT_MAPPER.mapToProductResource(productRepository.findById(id).orElseThrow());
    }

    @Override
    public ProductResource updateProduct(Long id, ProductResource product) {
        Product productEntity = productRepository.findById(id).orElseThrow();
        productEntity.setName(product.getName());
        productEntity.setBrand(product.getBrand());
        productEntity.setType(product.getType());
        productEntity.setImage(product.getImage());
        productEntity.setPrice(product.getPrice());

        productRepository.save(productEntity);

        return PRODUCT_MAPPER.mapToProductResource(productEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResource> getAllProducts() {
        return PRODUCT_MAPPER.mapToProductResourceList(productRepository.findAll());
    }
}
