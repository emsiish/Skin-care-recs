package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.dto.AddProductRatingDto;
import com.example.skincarerecs.controller.dto.ProductRatingDto;
import com.example.skincarerecs.entity.ProductRating;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(uses = UserMapper.class, componentModel = "spring")
@Component
public interface ProductRatingMapper {
    //ProductRatingMapper PRODUCT_RATING_MAPPER = Mappers.getMapper(ProductRatingMapper.class);
    ProductRating mapToProductRating(AddProductRatingDto productRating);
    ProductRatingDto mapToProductRatingResource(ProductRating productRating);
    List<ProductRatingDto> mapToProductRatingResourceList(List<ProductRating> productRatingList);
}
