package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.resources.ProductRatingResource;
import com.example.skincarerecs.entity.ProductRating;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductRatingMapper {
    ProductRatingMapper PRODUCT_RATING_MAPPER = Mappers.getMapper(ProductRatingMapper.class);
    ProductRating mapToProductRating(ProductRatingResource productRating);
    ProductRatingResource mapToProductRatingResource(ProductRating productRating);
    List<ProductRatingResource> mapToProductRatingResourceList(List<ProductRating> productRatingList);
}
