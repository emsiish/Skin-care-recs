package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.resources.DoctorResource;

import java.util.List;

public interface DoctorService {
    DoctorResource addDoctor(DoctorResource doctor);

    List<DoctorResource> getAllDoctors();

    DoctorResource getDoctorById(Long id);

    DoctorResource updateDoctor(Long id, DoctorResource doctor);

    void deleteDoctor(Long id);
}
