package com.example.skincarerecs.controller;

import com.example.skincarerecs.controller.resources.DoctorResource;
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
    public DoctorResource addDoctor(@RequestBody DoctorResource doctor) {
        return doctorService.addDoctor(doctor);
    }

    @GetMapping
    public List<DoctorResource> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping(path = "/{id}")
    public DoctorResource getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @PutMapping(path = "/{id}")
    public DoctorResource updateDoctor(@PathVariable Long id, @RequestBody DoctorResource doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }
}
