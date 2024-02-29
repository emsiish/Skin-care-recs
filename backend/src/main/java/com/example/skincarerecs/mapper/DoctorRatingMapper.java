package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.dto.AddDoctorRatingDto;
import com.example.skincarerecs.controller.dto.DoctorRatingDto;
import com.example.skincarerecs.entity.DoctorRating;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(uses = UserMapper.class, componentModel = "spring")
@Component
public interface DoctorRatingMapper {
    DoctorRating mapToDoctorRating(AddDoctorRatingDto doctorRatingDto);
    DoctorRatingDto mapToDoctorRatingDto(DoctorRating doctorRating);
    List<DoctorRatingDto> mapToDoctorRatingDtoList(List<DoctorRating> doctorRatingList);
}
