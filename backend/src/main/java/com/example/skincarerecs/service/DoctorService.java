package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.dto.DoctorDto;

import java.util.List;

public interface DoctorService {
    DoctorDto addDoctor(DoctorDto doctor);

    List<DoctorDto> getAllDoctors();

    DoctorDto getDoctorById(Long id);

    DoctorDto updateDoctor(Long id, DoctorDto doctor);

    void deleteDoctor(Long id);
}
