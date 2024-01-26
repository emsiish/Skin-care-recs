package com.example.skincarerecs.mapper;

import org.mapstruct.Mapper;

import com.example.skincarerecs.controller.resources.DoctorResource;
import com.example.skincarerecs.entity.Doctor;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DoctorMapper {
    DoctorMapper DOCTOR_MAPPER = Mappers.getMapper(DoctorMapper.class);
    Doctor mapToDoctor(DoctorResource doctorResource);
    DoctorResource mapToDoctorResource(Doctor doctor);

    List<DoctorResource> mapToDoctorResourceList(List<Doctor> doctorList);

}
