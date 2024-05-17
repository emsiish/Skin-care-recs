package com.example.skincarerecs.repository;

import com.example.skincarerecs.entity.DoctorRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRatingRepository extends JpaRepository<DoctorRating, Long> {
    @Query("SELECT ROUND(AVG(dr.rating), 1) FROM DoctorRating dr WHERE dr.doctor.id = :doctorId")
    Double getAverageRatingByDoctorId(Long doctorId);

    @Query("SELECT COUNT(dr) FROM DoctorRating dr WHERE dr.doctor.id = :doctorId")
    int countByDoctorId(Long doctorId);

    List<DoctorRating> findAllByDoctorId(Long doctorId);
}
