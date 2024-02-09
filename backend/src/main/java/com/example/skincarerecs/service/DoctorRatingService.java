package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.dto.AddDoctorRatingDto;
import com.example.skincarerecs.controller.dto.DoctorRatingDto;

import java.util.List;

public interface DoctorRatingService {
    DoctorRatingDto addDoctorRating(Long doctorId, AddDoctorRatingDto doctorRating);
    List<DoctorRatingDto> getAllDoctorRatings(Long doctorId);
    DoctorRatingDto getDoctorRatingById(Long doctorId, Long id);
    void deleteDoctorRating(Long doctorId, Long id);
}
