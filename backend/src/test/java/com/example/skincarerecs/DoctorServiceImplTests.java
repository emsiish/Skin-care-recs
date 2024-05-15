package com.example.skincarerecs;

import com.example.skincarerecs.controller.dto.DoctorDto;
import com.example.skincarerecs.controller.dto.DoctorRatingSummaryDto;
import com.example.skincarerecs.entity.Doctor;
import com.example.skincarerecs.mapper.DoctorMapper;
import com.example.skincarerecs.mapper.DoctorMapperImpl;
import com.example.skincarerecs.repository.DoctorRatingRepository;
import com.example.skincarerecs.repository.DoctorRepository;
import com.example.skincarerecs.service.impl.DoctorServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DoctorServiceImplTests {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private DoctorMapper doctorMapper;

    @Mock
    private DoctorRatingRepository doctorRatingRepository;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    AutoCloseable openMocks;

    @BeforeEach
    public void setUp() {
       openMocks =  MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDoctor() {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setName("Dr. John Doe");

        Doctor doctorEntity = new Doctor();
        doctorEntity.setName("Dr. John Doe");

        when(doctorMapper.mapToDoctor(doctorDto)).thenReturn(doctorEntity);
        when(doctorRepository.save(doctorEntity)).thenReturn(doctorEntity);
        when(doctorMapper.mapToDoctorDto(doctorEntity)).thenReturn(doctorDto);

        DoctorDto addedDoctor = doctorService.addDoctor(doctorDto);

        assertEquals(doctorDto.getName(), addedDoctor.getName());
        verify(doctorRepository, times(1)).save(doctorEntity);
    }

    @Test
    public void testGetAllDoctorsWithRatings() {
        Doctor doctor1 = new Doctor();
        doctor1.setId(1L);
        Doctor doctor2 = new Doctor();
        doctor2.setId(2L);

        when(doctorRepository.findAll()).thenReturn(Arrays.asList(doctor1, doctor2));
        when(doctorRatingRepository.getAverageRatingByDoctorId(1L)).thenReturn(4.5);
        when(doctorRatingRepository.countByDoctorId(1L)).thenReturn(10);
        when(doctorRatingRepository.getAverageRatingByDoctorId(2L)).thenReturn(3.8);
        when(doctorRatingRepository.countByDoctorId(2L)).thenReturn(8);
        when(doctorMapper.mapToDoctorRatingSummaryDtoList(Arrays.asList(doctor1, doctor2))).thenReturn(new DoctorMapperImpl().mapToDoctorRatingSummaryDtoList(Arrays.asList(doctor1, doctor2)));

        List<DoctorRatingSummaryDto> doctorRatingSummaries = doctorService.getAllDoctorsWithRatings();

        assertEquals(2, doctorRatingSummaries.size());
    }

    @Test
    public void testGetDoctorById() {
        Long id = 1L;
        Doctor doctorEntity = new Doctor();
        doctorEntity.setId(id);
        doctorEntity.setName("Dr. John Doe");

        DoctorDto expectedDoctorDto = new DoctorDto();
        expectedDoctorDto.setId(id);
        expectedDoctorDto.setName("Dr. John Doe");

        when(doctorRepository.findById(id)).thenReturn(Optional.of(doctorEntity));
        when(doctorMapper.mapToDoctorDto(doctorEntity)).thenReturn(expectedDoctorDto);

        DoctorDto retrievedDoctor = doctorService.getDoctorById(id);

        assertNotNull(retrievedDoctor);
        assertEquals(expectedDoctorDto.getId(), retrievedDoctor.getId());
        assertEquals(expectedDoctorDto.getName(), retrievedDoctor.getName());
    }

    @Test
    public void testUpdateDoctor() {
        Long id = 1L;
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(id);
        doctorDto.setName("Dr. Jane Smith");
        doctorDto.setPhoneNumber("1234567890");
        doctorDto.setEmail("jane@example.com");
        doctorDto.setHospital("City Hospital");
        doctorDto.setImage("image.jpg");

        Doctor existingDoctor = new Doctor();
        existingDoctor.setId(id);
        existingDoctor.setName("Dr. John Doe");

        when(doctorRepository.findById(id)).thenReturn(Optional.of(existingDoctor));
        when(doctorMapper.mapToDoctor(doctorDto)).thenReturn(existingDoctor);
        when(doctorRepository.save(existingDoctor)).thenReturn(existingDoctor);
        when(doctorMapper.mapToDoctorDto(existingDoctor)).thenReturn(doctorDto);

        DoctorDto updatedDoctor = doctorService.updateDoctor(id, doctorDto);

        assertNotNull(updatedDoctor);
        assertEquals(doctorDto.getName(), updatedDoctor.getName());
        assertEquals(doctorDto.getPhoneNumber(), updatedDoctor.getPhoneNumber());
        assertEquals(doctorDto.getEmail(), updatedDoctor.getEmail());
        assertEquals(doctorDto.getHospital(), updatedDoctor.getHospital());
        assertEquals(doctorDto.getImage(), updatedDoctor.getImage());
    }

    @Test
    public void testDeleteDoctor() {
        Long id = 1L;

        assertDoesNotThrow(() -> doctorService.deleteDoctor(id));

        verify(doctorRepository, times(1)).deleteById(id);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }
}
