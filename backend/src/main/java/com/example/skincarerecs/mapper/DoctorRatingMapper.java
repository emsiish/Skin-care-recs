package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.dto.DoctorRatingDto;
import com.example.skincarerecs.entity.DoctorRating;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(uses = UserMapper.class, componentModel = "spring")
@Component
public interface DoctorRatingMapper {
    //DoctorRatingMapper DOCTOR_RATING_MAPPER = Mappers.getMapper(DoctorRatingMapper.class);
    DoctorRating mapToDoctorRating(DoctorRatingDto doctorRatingDto);
    DoctorRatingDto mapToDoctorRatingResource(DoctorRating doctorRating);
    List<DoctorRatingDto> mapToDoctorRatingResourceList(List<DoctorRating> doctorRatingList);
}
