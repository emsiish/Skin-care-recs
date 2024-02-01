package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.controller.dto.DoctorRatingDto;
import com.example.skincarerecs.entity.DoctorRating;
import com.example.skincarerecs.repository.DoctorRatingRepository;
import com.example.skincarerecs.repository.DoctorRepository;
import com.example.skincarerecs.repository.UserRepository;
import com.example.skincarerecs.service.DoctorRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.skincarerecs.mapper.DoctorRatingMapper.DOCTOR_RATING_MAPPER;

@Service
@RequiredArgsConstructor
public class DoctorRatingServiceImpl implements DoctorRatingService {

    private final DoctorRatingRepository doctorRatingRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public DoctorRatingDto addDoctorRating(Long doctorId, DoctorRatingDto doctorRating) {
        DoctorRating doctorRatingEntity = DOCTOR_RATING_MAPPER.mapToDoctorRating(doctorRating);

        //TODO: all relationships should be set that way (resource -> string; entity -> object)
        //User userEntity = userRepository.findByUsername(doctorRating.getUsername()).orElseThrow();
        //Doctor doctorEntity = doctorRepository.findById(doctorId).orElseThrow();

        //doctorRatingEntity.setUser(userEntity);
        //doctorRatingEntity.setDoctor(doctorEntity);

        doctorRatingRepository.save(doctorRatingEntity);

        return DOCTOR_RATING_MAPPER.mapToDoctorRatingResource(doctorRatingEntity);
    }

    @Override
    public List<DoctorRatingDto> getAllDoctorRatings(Long doctorId) {
        return DOCTOR_RATING_MAPPER.mapToDoctorRatingResourceList(doctorRatingRepository.findAll());
    }

    @Override
    public DoctorRatingDto getDoctorRatingById(Long doctorId, Long id) {
        return DOCTOR_RATING_MAPPER.mapToDoctorRatingResource(doctorRatingRepository.findById(id).orElseThrow());
    }

    @Override
    public DoctorRatingDto updateDoctorRating(Long doctorId, Long id, DoctorRatingDto doctorRating) {
        return null;
    }

    @Override
    public void deleteDoctorRating(Long doctorId, Long id) {
        doctorRatingRepository.deleteById(id);
    }
}
