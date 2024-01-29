package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.resources.ProductResource;
import com.example.skincarerecs.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);
    Product mapToProduct(ProductResource productResource);
    ProductResource mapToProductResource(Product product);

    List<ProductResource> mapToProductResourceList(List<Product> all);
}
