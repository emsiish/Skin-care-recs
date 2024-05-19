package com.example.skincarerecs;

import com.example.skincarerecs.controller.dto.TagDto;
import com.example.skincarerecs.controller.dto.UserDto;
import com.example.skincarerecs.entity.Tag;
import com.example.skincarerecs.entity.User;
import com.example.skincarerecs.mapper.UserMapper;
import com.example.skincarerecs.repository.TagRepository;
import com.example.skincarerecs.repository.UserRepository;
import com.example.skincarerecs.service.impl.UserServiceImpl;
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

public class UserServiceImplTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TagRepository tagRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    AutoCloseable openMocks;

    @BeforeEach
    public void setUp() {
        openMocks =  MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser() {
        UserDto userDto = new UserDto();
        userDto.setEmail("test@example.com");
        TagDto tagDto = new TagDto();
        tagDto.setName("Oily");
        userDto.setTags(List.of(tagDto));

        User userEntity = new User();
        userEntity.setEmail("test@example.com");
        Tag tagEntity = new Tag();
        tagEntity.setName("Oily");

        when(userMapper.mapToUser(userDto)).thenReturn(userEntity);
        when(tagRepository.findByName("Oily")).thenReturn(Optional.of(tagEntity));
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.mapToUserDto(userEntity)).thenReturn(userDto);

        UserDto result = userService.addUser(userDto);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        verify(userRepository, times(1)).save(userEntity);
        verify(tagRepository, times(1)).findByName("Oily");
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        UserDto userDto1 = new UserDto();
        UserDto userDto2 = new UserDto();

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(userMapper.mapToUserDtoList(Arrays.asList(user1, user2))).thenReturn(Arrays.asList(userDto1, userDto2));

        List<UserDto> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        UserDto userDto = new UserDto();
        userDto.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        UserDto result = userService.getUserById(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        UserDto userDto = new UserDto();
        userDto.setId(userId);
        userDto.setName("Updated Name");
        userDto.setEmail("updated@example.com");
        userDto.setPassword("updatedpassword");

        User userEntity = new User();
        userEntity.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.mapToUserDto(userEntity)).thenReturn(userDto);

        UserDto result = userService.updateUser(userId, userDto);

        assertNotNull(result);
        assertEquals("Updated Name", userEntity.getName());
        assertEquals("updated@example.com", userEntity.getEmail());
        assertEquals("updatedpassword", userEntity.getPassword());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    public void testUpdateUserTags() {
        Long userId = 1L;
        TagDto tagDto = new TagDto();
        tagDto.setName("Oily");
        List<TagDto> tagsDto = List.of(tagDto);

        User userEntity = new User();
        userEntity.setId(userId);
        userEntity.setEmail("test@example.com");
        Tag tagEntity = new Tag();
        tagEntity.setName("Oily");

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(tagRepository.findByName("Oily")).thenReturn(Optional.of(tagEntity));
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.mapToUserDto(userEntity)).thenReturn(new UserDto());

        UserDto result = userService.updateUserTags(userId, tagsDto);

        assertNotNull(result);
        assertEquals(1, userEntity.getTags().size());
        assertEquals("Oily", userEntity.getTags().get(0).getName());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(userEntity);
        verify(tagRepository, times(1)).findByName("Oily");
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        assertDoesNotThrow(() -> userService.deleteUser(userId));

        verify(userRepository, times(1)).deleteById(userId);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }
}
