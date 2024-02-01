package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.controller.dto.TagDto;
import com.example.skincarerecs.controller.dto.UserDto;
import com.example.skincarerecs.entity.Tag;
import com.example.skincarerecs.entity.User;
import com.example.skincarerecs.repository.UserRepository;
import com.example.skincarerecs.repository.TagRepository;
import com.example.skincarerecs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.skincarerecs.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    @Override
    public UserDto addUser(UserDto user) {
        User userEntity = USER_MAPPER.mapToUser(user);
        userRepository.save(userEntity);

        //TODO: setting tags manually. won't be used by frontend. mapper doesn't know how to tag -> string
        //UserResource userResource = USER_MAPPER.mapToUserResource(userEntity);
        //userResource.setTags_names(user.getTags_names());

        return USER_MAPPER.mapToUserResource(userEntity);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return USER_MAPPER.mapToUserResourceList(userRepository.findAll());
    }
    @Override
    public UserDto getUserById(Long id) {
        return USER_MAPPER.mapToUserResource(userRepository.findById(id).orElseThrow());
    }

    @Override
    public UserDto updateUser(Long id, UserDto user) {
        User userEntity = userRepository.findById(id).orElseThrow();

        userEntity.setName(userEntity.getName());
        userEntity.setUsername(userEntity.getUsername());
        userEntity.setPassword(userEntity.getPassword());

        userRepository.save(userEntity);

        return USER_MAPPER.mapToUserResource(userEntity);
    }

    @Override
    public UserDto updateUserTags(Long id, List<TagDto> tags) {
        System.out.println("tags: " + tags);
        User userEntity = userRepository.findById(id).orElseThrow();
        List<Tag> tagsEntity = tags.stream()
                .map(tag -> tagRepository.findByName(tag.getName()).orElseThrow())
                .collect(Collectors.toCollection(ArrayList::new));

        userEntity.setTags(tagsEntity);
        userRepository.save(userEntity);

        return USER_MAPPER.mapToUserResource(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
