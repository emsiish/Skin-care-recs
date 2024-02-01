package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.controller.dto.DoctorDto;
import com.example.skincarerecs.entity.Doctor;
import com.example.skincarerecs.repository.DoctorRepository;
import com.example.skincarerecs.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.example.skincarerecs.mapper.DoctorMapper.DOCTOR_MAPPER;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    @Override
    public DoctorDto addDoctor(DoctorDto doctor) {
        Doctor doctorEntity = DOCTOR_MAPPER.mapToDoctor(doctor);

        doctorRepository.save(doctorEntity);

        return DOCTOR_MAPPER.mapToDoctorResource(doctorEntity);
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        return DOCTOR_MAPPER.mapToDoctorResourceList(doctorRepository.findAll());
    }

    @Override
    public DoctorDto getDoctorById(Long id) {
        return DOCTOR_MAPPER.mapToDoctorResource(doctorRepository.findById(id).orElseThrow());
    }

    @Override
    public DoctorDto updateDoctor(Long id, DoctorDto doctor) {
        Doctor doctorEntity = doctorRepository.findById(id).orElseThrow();
        doctorEntity.setName(doctor.getName());
        doctorEntity.setTelephone(doctor.getTelephone());
        doctorEntity.setEmail(doctor.getEmail());
        doctorEntity.setHospital(doctor.getHospital());

        doctorRepository.save(doctorEntity);

        return DOCTOR_MAPPER.mapToDoctorResource(doctorEntity);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
