package com.example.skincarerecs.mapper;

import org.mapstruct.Mapper;

import com.example.skincarerecs.controller.dto.DoctorDto;
import com.example.skincarerecs.entity.Doctor;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DoctorMapper {
    DoctorMapper DOCTOR_MAPPER = Mappers.getMapper(DoctorMapper.class);
    Doctor mapToDoctor(DoctorDto doctorDto);
    DoctorDto mapToDoctorResource(Doctor doctor);

    List<DoctorDto> mapToDoctorResourceList(List<Doctor> doctorList);

}
