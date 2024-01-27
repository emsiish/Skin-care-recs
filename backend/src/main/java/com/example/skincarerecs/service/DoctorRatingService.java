package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.resources.DoctorRatingResource;

import java.util.List;

public interface DoctorRatingService {
    DoctorRatingResource addDoctorRating(Long doctorId, DoctorRatingResource doctorRating);
    List<DoctorRatingResource> getAllDoctorRatings(Long doctorId);
    DoctorRatingResource getDoctorRatingById(Long doctorId, Long id);

    //?? do i need it?
    DoctorRatingResource updateDoctorRating(Long doctorId, Long id, DoctorRatingResource doctorRating);
    void deleteDoctorRating(Long doctorId, Long id);
}
