package com.example.skincarerecs.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.skincarerecs.controller.dto.TagDto;
import com.example.skincarerecs.controller.dto.UserDto;
import com.example.skincarerecs.entity.Tag;
import com.example.skincarerecs.entity.User;
import com.example.skincarerecs.mapper.UserMapper;
import com.example.skincarerecs.repository.UserRepository;
import com.example.skincarerecs.repository.TagRepository;
import com.example.skincarerecs.service.UserService;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto addUser(UserDto user) {
        log.info("Adding a new user: {}", user);
        User userEntity = userMapper.mapToUser(user);
        userRepository.save(userEntity);
        log.info("User added successfully: {}", user);

        return userMapper.mapToUserResource(userEntity);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Fetching all users.");
        return userMapper.mapToUserResourceList(userRepository.findAll());
    }

    @Override
    public UserDto getUserById(Long id) {
        log.info("Fetching user by ID: {}", id);
        return userMapper.mapToUserResource(userRepository.findById(id).orElseThrow());
    }

    @Override
    public UserDto updateUser(Long id, UserDto user) {
        log.info("Updating user with ID {}: {}", id, user);
        User userEntity = userRepository.findById(id).orElseThrow();

        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        userRepository.save(userEntity);

        log.info("User updated successfully: {}", userEntity);

        return userMapper.mapToUserResource(userEntity);
    }

    @Override
    public UserDto updateUserTags(Long id, List<TagDto> tags) {
        log.info("Updating tags for user with ID {}: {}", id, tags);
        User userEntity = userRepository.findById(id).orElseThrow();
        List<Tag> tagsEntity = tags.stream()
                .map(tag -> tagRepository.findByName(tag.getName()).orElseThrow())
                .collect(Collectors.toCollection(ArrayList::new));

        userEntity.setTags(tagsEntity);
        userRepository.save(userEntity);

        log.info("User's tags updated successfully: {}", userEntity);

        return userMapper.mapToUserResource(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
        log.info("User deleted successfully with ID: {}", id);
    }
}
