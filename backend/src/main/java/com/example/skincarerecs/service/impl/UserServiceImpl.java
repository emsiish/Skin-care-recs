package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.controller.resources.UserResource;
import com.example.skincarerecs.entity.Tag;
import com.example.skincarerecs.entity.User;
import com.example.skincarerecs.repository.UserRepository;
import com.example.skincarerecs.repository.TagRepository;
import com.example.skincarerecs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.skincarerecs.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    @Override
    public UserResource addUser(UserResource user) {
        User userEntity = USER_MAPPER.mapToUser(user);
        List<Tag> tags = tagRepository.findAllByNameIn(user.getTags_names());

        userEntity.setTags(tags);

        userRepository.save(userEntity);

        //TODO: setting tags manually. won't be used by frontend. mapper doesn't know how to tag -> string
        UserResource userResource = USER_MAPPER.mapToUserResource(userEntity);
        userResource.setTags_names(user.getTags_names());

        return userResource;
    }

    @Override
    public List<UserResource> getAllUsers() {
        return USER_MAPPER.mapToUserResourceList(userRepository.findAll());
    }
    @Override
    public UserResource getUserById(Long id) {
        return USER_MAPPER.mapToUserResource(userRepository.findById(id).orElseThrow());
    }

    @Override
    public UserResource updateUser(Long id, UserResource user) {
        User userEntity = userRepository.findById(id).orElseThrow();

        userEntity.setName(userEntity.getName());
        userEntity.setUsername(userEntity.getUsername());
        userEntity.setPassword(userEntity.getPassword());

        userRepository.save(userEntity);

        return USER_MAPPER.mapToUserResource(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
