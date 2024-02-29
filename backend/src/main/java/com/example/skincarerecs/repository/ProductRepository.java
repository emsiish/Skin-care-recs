package com.example.skincarerecs.repository;

import com.example.skincarerecs.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p JOIN p.tags t WHERE t.name IN :tagNames")
    List<Product> findByTags(List<String> tagNames);
}
