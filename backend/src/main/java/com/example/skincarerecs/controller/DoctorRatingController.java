package com.example.skincarerecs.controller;

import com.example.skincarerecs.controller.dto.DoctorRatingDto;
import com.example.skincarerecs.service.DoctorRatingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/doctors/{doctorId}/ratings")
public class DoctorRatingController {

    private final DoctorRatingService doctorRatingService;

    @PostMapping
    public DoctorRatingDto addDoctorRating(@PathVariable Long doctorId, @RequestBody DoctorRatingDto doctorRating) {
        return doctorRatingService.addDoctorRating(doctorId, doctorRating);
    }

    @GetMapping
    public List<DoctorRatingDto> getAllDoctorRatings(@PathVariable Long doctorId) {
        return doctorRatingService.getAllDoctorRatings(doctorId);
    }

    @GetMapping(path = "/{id}")
    public DoctorRatingDto getDoctorRatingById(@PathVariable Long doctorId, @PathVariable Long id) {
        return doctorRatingService.getDoctorRatingById(doctorId, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteDoctorRating(@PathVariable Long doctorId, @PathVariable Long id) {
        doctorRatingService.deleteDoctorRating(doctorId, id);
    }
}
