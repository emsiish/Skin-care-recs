package com.example.skincarerecs.controller;

import com.example.skincarerecs.controller.dto.DoctorDto;
import com.example.skincarerecs.controller.dto.DoctorRatingHelperDto;
import com.example.skincarerecs.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping (path = "/api/v1/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping
    public DoctorDto addDoctor(@RequestBody DoctorDto doctor) {
        return doctorService.addDoctor(doctor);
    }

    @GetMapping
    public List<DoctorRatingHelperDto> getAllDoctors() {
        return doctorService.getAllDoctorsWithRatings();
    }

    @GetMapping(path = "/{id}")
    public DoctorDto getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @PutMapping(path = "/{id}")
    public DoctorDto updateDoctor(@PathVariable Long id, @RequestBody DoctorDto doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }
}
