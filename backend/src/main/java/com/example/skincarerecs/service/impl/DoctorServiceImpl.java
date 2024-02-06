package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.controller.dto.DoctorDto;
import com.example.skincarerecs.controller.dto.DoctorRatingHelperDto;
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
        log.info("Adding a new doctor: {}", doctor);
        Doctor doctorEntity = doctorMapper.mapToDoctor(doctor);
        doctorRepository.save(doctorEntity);
        log.info("Doctor added successfully: {}", doctor);
        return doctorMapper.mapToDoctorResource(doctorEntity);
    }

    @Override
    public List<DoctorRatingHelperDto> getAllDoctorsWithRatings() {
        log.info("Fetching all doctors with ratings.");

        List<Doctor> doctors = doctorRepository.findAll();

        List<DoctorRatingHelperDto> doctorRatingsHelper = doctorMapper.mapToDoctorRatingHelperResourceList(doctors);

        doctorRatingsHelper.forEach(doctorRatingHelper -> {
            doctorRatingHelper.setAverageRating(doctorRatingRepository.getAverageRatingByDoctorId(doctorRatingHelper.getId()));
            doctorRatingHelper.setCount(doctorRatingRepository.countByDoctorId(doctorRatingHelper.getId()));
        });

        return doctorRatingsHelper;
    }


    @Override
    public DoctorDto getDoctorById(Long id) {
        log.info("Fetching doctor by ID: {}", id);
        return doctorMapper.mapToDoctorResource(doctorRepository.findById(id).orElseThrow());
    }

    @Override
    public DoctorDto updateDoctor(Long id, DoctorDto doctor) {
        log.info("Updating doctor with ID {}: {}", id, doctor);
        Doctor doctorEntity = doctorRepository.findById(id).orElseThrow();
        doctorEntity.setName(doctor.getName());
        doctorEntity.setTelephone(doctor.getTelephone());
        doctorEntity.setEmail(doctor.getEmail());
        doctorEntity.setHospital(doctor.getHospital());

        doctorRepository.save(doctorEntity);

        log.info("Doctor updated successfully: {}", doctorEntity);

        return doctorMapper.mapToDoctorResource(doctorEntity);
    }

    @Override
    public void deleteDoctor(Long id) {
        log.info("Deleting doctor with ID: {}", id);
        doctorRepository.deleteById(id);
        log.info("Doctor deleted successfully with ID: {}", id);
    }
}
