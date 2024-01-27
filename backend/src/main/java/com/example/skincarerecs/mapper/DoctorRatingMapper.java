package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.resources.DoctorRatingResource;
import com.example.skincarerecs.entity.DoctorRating;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DoctorRatingMapper {
    DoctorRatingMapper DOCTOR_RATING_MAPPER = Mappers.getMapper(DoctorRatingMapper.class);
    DoctorRating mapToDoctorRating(DoctorRatingResource doctorRatingResource);
    DoctorRatingResource mapToDoctorRatingResource(DoctorRating doctorRating);
    List<DoctorRatingResource> mapToDoctorRatingResourceList(List<DoctorRating> doctorRatingList);
}
