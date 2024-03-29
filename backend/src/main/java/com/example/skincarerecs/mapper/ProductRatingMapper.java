package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.dto.AddProductRatingDto;
import com.example.skincarerecs.controller.dto.ProductRatingDto;
import com.example.skincarerecs.entity.ProductRating;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(uses = UserMapper.class, componentModel = "spring")
@Component
public interface ProductRatingMapper {
    ProductRating mapToProductRating(AddProductRatingDto productRating);
    ProductRatingDto mapToProductRatingDto(ProductRating productRating);
    List<ProductRatingDto> mapToProductRatingDtoList(List<ProductRating> productRatingList);
}
