package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.dto.AddDoctorDto;
import com.example.skincarerecs.controller.dto.DoctorRatingSummaryDto;
import org.mapstruct.Mapper;

import com.example.skincarerecs.controller.dto.DoctorDto;
import com.example.skincarerecs.entity.Doctor;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import javax.xml.stream.events.StartDocument;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface DoctorMapper {

    @Mapping(target = "image", ignore = true)
    Doctor mapToDoctor(AddDoctorDto doctorDto);
    DoctorDto mapToDoctorDto(Doctor doctor);
    List<DoctorRatingSummaryDto> mapToDoctorRatingSummaryDtoList(List<Doctor> doctors);
}
