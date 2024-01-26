package com.example.skincarerecs.repository;

import com.example.skincarerecs.entity.DoctorRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRatingRepository extends JpaRepository<DoctorRating, Long> {
}
