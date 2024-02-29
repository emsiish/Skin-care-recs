package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.dto.DoctorRatingSummaryDto;
import org.mapstruct.Mapper;

import com.example.skincarerecs.controller.dto.DoctorDto;
import com.example.skincarerecs.entity.Doctor;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface DoctorMapper {
    Doctor mapToDoctor(DoctorDto doctorDto);
    DoctorDto mapToDoctorDto(Doctor doctor);
    List<DoctorRatingSummaryDto> mapToDoctorRatingSummaryDtoList(List<Doctor> doctors);
}
