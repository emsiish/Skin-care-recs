package com.example.skincarerecs.repository;

import com.example.skincarerecs.entity.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {

    @Query("SELECT AVG(pr.rating) FROM ProductRating pr WHERE pr.product.id = :productId")
    Double getAverageRatingByProductId(Long productId);

    @Query("SELECT COUNT(pr) FROM ProductRating pr WHERE pr.product.id = :productId")
    int countByProductId(Long productId);

    List<ProductRating> findAllByProductId(Long productId);
}
