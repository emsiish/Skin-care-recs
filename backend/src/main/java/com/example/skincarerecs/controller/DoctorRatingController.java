package com.example.skincarerecs.controller;

import com.example.skincarerecs.controller.resources.DoctorRatingResource;
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
    public DoctorRatingResource addDoctorRating(@PathVariable Long doctorId, @RequestBody DoctorRatingResource doctorRating) {
        return doctorRatingService.addDoctorRating(doctorId, doctorRating);
    }

    @GetMapping
    public List<DoctorRatingResource> getAllDoctorRatings(@PathVariable Long doctorId) {
        return doctorRatingService.getAllDoctorRatings(doctorId);
    }

    @GetMapping(path = "/{id}")
    public DoctorRatingResource getDoctorRatingById(@PathVariable Long doctorId, @PathVariable Long id) {
        return doctorRatingService.getDoctorRatingById(doctorId, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteDoctorRating(@PathVariable Long doctorId, @PathVariable Long id) {
        doctorRatingService.deleteDoctorRating(doctorId, id);
    }
}
