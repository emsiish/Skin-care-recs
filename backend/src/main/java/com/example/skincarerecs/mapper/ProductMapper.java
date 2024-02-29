package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.dto.ProductDto;
import com.example.skincarerecs.controller.dto.ProductRatingSummaryDto;
import com.example.skincarerecs.entity.Product;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(uses = {TagMapper.class}, componentModel = "spring")
public interface ProductMapper {
    Product mapToProduct(ProductDto productDto);
    ProductDto mapToProductDto(Product product);
    List<ProductDto> mapToProductDtoList(List<Product> all);
    ProductRatingSummaryDto mapToProductRatingSummaryDto(Product product);
    List<ProductRatingSummaryDto> mapToProductRatingSummaryDtoList(List<Product> all);
}
