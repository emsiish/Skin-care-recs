package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.dto.DoctorDto;
import com.example.skincarerecs.controller.dto.DoctorRatingHelperDto;

import java.util.List;

public interface DoctorService {
    DoctorDto addDoctor(DoctorDto doctor);

    List<DoctorRatingHelperDto> getAllDoctorsWithRatings();

    DoctorDto getDoctorById(Long id);

    DoctorDto updateDoctor(Long id, DoctorDto doctor);

    void deleteDoctor(Long id);
}
