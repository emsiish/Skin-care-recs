package com.example.skincarerecs;

import com.example.skincarerecs.controller.dto.AddDoctorRatingDto;
import com.example.skincarerecs.controller.dto.DoctorRatingDto;
import com.example.skincarerecs.entity.Doctor;
import com.example.skincarerecs.entity.DoctorRating;
import com.example.skincarerecs.entity.User;
import com.example.skincarerecs.mapper.DoctorRatingMapper;
import com.example.skincarerecs.repository.DoctorRatingRepository;
import com.example.skincarerecs.repository.DoctorRepository;
import com.example.skincarerecs.repository.UserRepository;
import com.example.skincarerecs.service.impl.DoctorRatingServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DoctorRatingServiceImplTests {

    @Mock
    private DoctorRatingRepository doctorRatingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private DoctorRatingMapper doctorRatingMapper;

    @InjectMocks
    private DoctorRatingServiceImpl doctorRatingService;

    AutoCloseable openMocks;

    @BeforeEach
    public void setUp() {
        openMocks =  MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDoctorRating() {
        Long doctorId = 1L;
        AddDoctorRatingDto addDoctorRatingDto = new AddDoctorRatingDto();
        addDoctorRatingDto.setRating(5.0);

        DoctorRating doctorRatingEntity = new DoctorRating();
        doctorRatingEntity.setRating(5.0);

        String currentUserEmail = "test@example.com";
        User userEntity = new User();
        userEntity.setEmail(currentUserEmail);

        Doctor doctorEntity = new Doctor();
        doctorEntity.setId(doctorId);

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(currentUserEmail);
        SecurityContextHolder.setContext(securityContext);

        when(userRepository.findByEmail(currentUserEmail)).thenReturn(Optional.of(userEntity));
        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctorEntity));
        when(doctorRatingMapper.mapToDoctorRating(addDoctorRatingDto)).thenReturn(doctorRatingEntity);
        when(doctorRatingRepository.save(doctorRatingEntity)).thenReturn(doctorRatingEntity);
        DoctorRatingDto doctorRatingDto = new DoctorRatingDto();
        when(doctorRatingMapper.mapToDoctorRatingDto(doctorRatingEntity)).thenReturn(doctorRatingDto);

        DoctorRatingDto result = doctorRatingService.addDoctorRating(doctorId, addDoctorRatingDto);

        assertNotNull(result);
        verify(doctorRatingRepository, times(1)).save(doctorRatingEntity);
        assertEquals(userEntity, doctorRatingEntity.getUser());
        assertEquals(doctorEntity, doctorRatingEntity.getDoctor());
    }

    @Test
    public void testGetAllDoctorRatings() {
        Long doctorId = 1L;
        DoctorRating doctorRating1 = new DoctorRating();
        doctorRating1.setId(1L);
        DoctorRating doctorRating2 = new DoctorRating();
        doctorRating2.setId(2L);

        List<DoctorRating> doctorRatings = Arrays.asList(doctorRating1, doctorRating2);
        List<DoctorRatingDto> doctorRatingDtos = Arrays.asList(new DoctorRatingDto(), new DoctorRatingDto());

        when(doctorRatingRepository.findAllByDoctorId(doctorId)).thenReturn(doctorRatings);
        when(doctorRatingMapper.mapToDoctorRatingDtoList(doctorRatings)).thenReturn(doctorRatingDtos);

        List<DoctorRatingDto> result = doctorRatingService.getAllDoctorRatings(doctorId);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(doctorRatingRepository, times(1)).findAllByDoctorId(doctorId);
    }

    @Test
    public void testGetDoctorRatingById() {
        Long doctorId = 1L;
        Long ratingId = 1L;
        DoctorRating doctorRating = new DoctorRating();
        doctorRating.setId(ratingId);

        DoctorRatingDto doctorRatingDto = new DoctorRatingDto();
        doctorRatingDto.setId(ratingId);

        when(doctorRatingRepository.findById(ratingId)).thenReturn(Optional.of(doctorRating));
        when(doctorRatingMapper.mapToDoctorRatingDto(doctorRating)).thenReturn(doctorRatingDto);

        DoctorRatingDto result = doctorRatingService.getDoctorRatingById(doctorId, ratingId);

        assertNotNull(result);
        assertEquals(ratingId, result.getId());
        verify(doctorRatingRepository, times(1)).findById(ratingId);
    }

    @Test
    public void testDeleteDoctorRating() {
        Long doctorId = 1L;
        Long ratingId = 1L;

        assertDoesNotThrow(() -> doctorRatingService.deleteDoctorRating(doctorId, ratingId));

        verify(doctorRatingRepository, times(1)).deleteById(ratingId);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }
}
