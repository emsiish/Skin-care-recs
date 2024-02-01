package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.dto.ProductDto;
import com.example.skincarerecs.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);
    Product mapToProduct(ProductDto productDto);
    ProductDto mapToProductResource(Product product);

    List<ProductDto> mapToProductResourceList(List<Product> all);
}
