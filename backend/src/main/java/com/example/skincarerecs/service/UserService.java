package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.dto.TagDto;
import com.example.skincarerecs.controller.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto user);
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto user);
    UserDto updateUserTags(Long id, List<TagDto> tags);
    void deleteUser(Long id);

    List<UserDto> getAllUsers();
}
