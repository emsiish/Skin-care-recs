package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.controller.dto.DoctorDto;
import com.example.skincarerecs.controller.dto.DoctorRatingSummaryDto;
import com.example.skincarerecs.entity.Doctor;
import com.example.skincarerecs.mapper.DoctorMapper;
import com.example.skincarerecs.repository.DoctorRatingRepository;
import com.example.skincarerecs.repository.DoctorRepository;
import com.example.skincarerecs.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final DoctorRatingRepository doctorRatingRepository;

    @Override
    public DoctorDto addDoctor(DoctorDto doctor) {
        log.info("Adding a new doctor: {}", doctor.getName());
        Doctor doctorEntity = doctorMapper.mapToDoctor(doctor);
        doctorRepository.save(doctorEntity);
        log.info("Doctor added successfully: {}", doctor.getName());
        return doctorMapper.mapToDoctorDto(doctorEntity);
    }

    @Override
    public List<DoctorRatingSummaryDto> getAllDoctorsWithRatings() {
        log.info("Fetching all doctors with ratings.");

        List<Doctor> doctors = doctorRepository.findAll();

        List<DoctorRatingSummaryDto> doctorRatingsHelper = doctorMapper.mapToDoctorRatingSummaryDtoList(doctors);

        doctorRatingsHelper.forEach(doctorRatingHelper -> {
            doctorRatingHelper.setAverageRating(doctorRatingRepository.getAverageRatingByDoctorId(doctorRatingHelper.getId()));
            doctorRatingHelper.setCount(doctorRatingRepository.countByDoctorId(doctorRatingHelper.getId()));
        });

        return doctorRatingsHelper;
    }


    @Override
    public DoctorDto getDoctorById(Long id) {
        log.info("Fetching doctor by ID: {}", id);
        return doctorMapper.mapToDoctorDto(doctorRepository.findById(id).orElseThrow());
    }

    @Override
    public DoctorDto updateDoctor(Long id, DoctorDto doctor) {
        log.info("Updating doctor with ID {}.", id);
        Doctor doctorEntity = doctorRepository.findById(id).orElseThrow();
        doctorEntity.setName(doctor.getName());
        doctorEntity.setPhoneNumber(doctor.getPhoneNumber());
        doctorEntity.setEmail(doctor.getEmail());
        doctorEntity.setHospital(doctor.getHospital());
        doctorEntity.setImage(doctor.getImage());

        doctorRepository.save(doctorEntity);

        log.info("Doctor updated successfully: {}", doctorEntity.getName());

        return doctorMapper.mapToDoctorDto(doctorEntity);
    }

    @Override
    public void deleteDoctor(Long id) {
        log.info("Deleting doctor with ID: {}", id);
        doctorRepository.deleteById(id);
        log.info("Doctor deleted successfully with ID: {}", id);
    }
}
