package com.example.skincarerecs;

import com.example.skincarerecs.config.JwtService;
import com.example.skincarerecs.controller.DoctorRatingController;
import com.example.skincarerecs.controller.dto.AddDoctorRatingDto;
import com.example.skincarerecs.controller.dto.DoctorRatingDto;
import com.example.skincarerecs.service.DoctorRatingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DoctorRatingController.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@AutoConfigureMockMvc(addFilters = false)
public class DoctorRatingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorRatingService doctorRatingService;

    @MockBean
    private JwtService jwtService;

    @Test
    public void testAddDoctorRating() throws Exception {
        AddDoctorRatingDto addDoctorRatingDto = new AddDoctorRatingDto();
        addDoctorRatingDto.setRating(5.0); // Set rating value

        when(doctorRatingService.addDoctorRating(any(Long.class), any(AddDoctorRatingDto.class)))
                .thenReturn(new DoctorRatingDto());

        mockMvc.perform(post("/api/v1/doctors/123/ratings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(addDoctorRatingDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllDoctorRatings() throws Exception {
        DoctorRatingDto rating1 = new DoctorRatingDto();
        DoctorRatingDto rating2 = new DoctorRatingDto();
        List<DoctorRatingDto> ratings = Arrays.asList(rating1, rating2);

        when(doctorRatingService.getAllDoctorRatings(any(Long.class))).thenReturn(ratings);

        mockMvc.perform(get("/api/v1/doctors/123/ratings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetDoctorRatingById() throws Exception {
        DoctorRatingDto rating = new DoctorRatingDto();

        when(doctorRatingService.getDoctorRatingById(any(Long.class), any(Long.class)))
                .thenReturn(rating);

        mockMvc.perform(get("/api/v1/doctors/123/ratings/456"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteDoctorRating() throws Exception {
        mockMvc.perform(delete("/api/v1/doctors/123/ratings/456"))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
