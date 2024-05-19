package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.dto.AddDoctorDto;
import com.example.skincarerecs.controller.dto.DoctorDto;
import com.example.skincarerecs.controller.dto.DoctorRatingSummaryDto;

import java.io.IOException;
import java.util.List;

public interface DoctorService {
    DoctorDto addDoctor(AddDoctorDto doctor) throws IOException;

    List<DoctorRatingSummaryDto> getAllDoctorsWithRatings();

    DoctorDto getDoctorById(Long id);

    DoctorDto updateDoctor(Long id, DoctorDto doctor);

    void deleteDoctor(Long id);
}
