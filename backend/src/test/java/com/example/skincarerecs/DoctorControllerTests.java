package com.example.skincarerecs;

import com.example.skincarerecs.config.JwtService;
import com.example.skincarerecs.controller.DoctorController;
import com.example.skincarerecs.controller.dto.AddDoctorDto;
import com.example.skincarerecs.controller.dto.DoctorDto;
import com.example.skincarerecs.controller.dto.DoctorRatingSummaryDto;
import com.example.skincarerecs.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;


import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DoctorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DoctorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    private DoctorDto doctorDto;
    private DoctorRatingSummaryDto doctorRatingSummaryDto;

    @BeforeEach
    public void setUp() {
        doctorDto = new DoctorDto();
        doctorDto.setId(1L);
        doctorDto.setName("Dr. John Doe");
        doctorDto.setPhoneNumber("1234567890");
        doctorDto.setEmail("john.doe@example.com");
        doctorDto.setHospital("City Hospital");
        doctorDto.setImage("image_url");

        doctorRatingSummaryDto = new DoctorRatingSummaryDto();
        doctorRatingSummaryDto.setId(1L);
        doctorRatingSummaryDto.setName("Dr. John Doe");
        doctorRatingSummaryDto.setAverageRating(4.5);
        doctorRatingSummaryDto.setCount(10);
    }

    @Test
    public void testAddDoctor() throws Exception {
        MockMultipartFile imageFile = new MockMultipartFile(
                "image",
                "doctor.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "image content".getBytes()
        );

        when(doctorService.addDoctor(any(AddDoctorDto.class))).thenReturn(doctorDto);

        this.mockMvc.perform(multipart("/api/v1/doctors")
                        .file(imageFile)
                        .param("name", "bebe")
                        .param("phoneNumber", "0888")
                        .param("email", "bebe@gmail.com")
                        .param("hospital", "beb hospital")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Dr. John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    public void testGetAllDoctors() throws Exception {
        List<DoctorRatingSummaryDto> doctorRatingSummaryDtos = Arrays.asList(doctorRatingSummaryDto);
        when(doctorService.getAllDoctorsWithRatings()).thenReturn(doctorRatingSummaryDtos);

        mockMvc.perform(get("/api/v1/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Dr. John Doe"))
                .andExpect(jsonPath("$[0].averageRating").value(4.5));
    }

    @Test
    public void testGetDoctorById() throws Exception {
        when(doctorService.getDoctorById(anyLong())).thenReturn(doctorDto);

        mockMvc.perform(get("/api/v1/doctors/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Dr. John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    public void testUpdateDoctor() throws Exception {
        when(doctorService.updateDoctor(anyLong(), any(DoctorDto.class))).thenReturn(doctorDto);

        mockMvc.perform(put("/api/v1/doctors/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctorDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Dr. John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    public void testDeleteDoctor() throws Exception {
        mockMvc.perform(delete("/api/v1/doctors/{id}", 1L))
                .andExpect(status().isOk());

        verify(doctorService, times(1)).deleteDoctor(1L);
    }
}
