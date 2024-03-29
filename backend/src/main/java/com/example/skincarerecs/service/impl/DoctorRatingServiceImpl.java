package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.controller.dto.AddDoctorRatingDto;
import com.example.skincarerecs.controller.dto.DoctorRatingDto;
import com.example.skincarerecs.entity.Doctor;
import com.example.skincarerecs.entity.DoctorRating;
import com.example.skincarerecs.entity.User;
import com.example.skincarerecs.mapper.DoctorRatingMapper;
import com.example.skincarerecs.repository.DoctorRatingRepository;
import com.example.skincarerecs.repository.DoctorRepository;
import com.example.skincarerecs.repository.UserRepository;
import com.example.skincarerecs.service.DoctorRatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorRatingServiceImpl implements DoctorRatingService {

    private final DoctorRatingRepository doctorRatingRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorRatingMapper doctorRatingMapper;

    @Override
    public DoctorRatingDto addDoctorRating(Long doctorId, AddDoctorRatingDto doctorRating) {
        log.info("Adding a new doctor rating for doctor ID {}.", doctorId);
        DoctorRating doctorRatingEntity = doctorRatingMapper.mapToDoctorRating(doctorRating);

        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User userEntity = userRepository.findByEmail(currentUserEmail).orElseThrow();
        Doctor doctorEntity = doctorRepository.findById(doctorId).orElseThrow();
        doctorRatingEntity.setUser(userEntity);
        doctorRatingEntity.setDoctor(doctorEntity);

        doctorRatingRepository.save(doctorRatingEntity);

        log.info("Doctor rating added successfully for doctor ID {}.", doctorId);

        return doctorRatingMapper.mapToDoctorRatingDto(doctorRatingEntity);
    }

    @Override
    public List<DoctorRatingDto> getAllDoctorRatings(Long doctorId) {
        log.info("Fetching all doctor ratings for doctor ID: {}", doctorId);
        return doctorRatingMapper.mapToDoctorRatingDtoList(doctorRatingRepository.findAllByDoctorId(doctorId));
    }

    @Override
    public DoctorRatingDto getDoctorRatingById(Long doctorId, Long id) {
        log.info("Fetching doctor rating by ID {} for doctor ID: {}", id, doctorId);
        return doctorRatingMapper.mapToDoctorRatingDto(doctorRatingRepository.findById(id).orElseThrow());
    }

    @Override
    public void deleteDoctorRating(Long doctorId, Long id) {
        log.info("Deleting doctor rating with ID {} for doctor ID: {}", id, doctorId);
        doctorRatingRepository.deleteById(id);
        log.info("Doctor rating deleted successfully with ID {} for doctor ID: {}", id, doctorId);
    }
}
