package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.resources.UserResource;

import java.util.List;

public interface UserService {
    UserResource addUser(UserResource user);
    UserResource getUserById(Long id);
    UserResource updateUser(Long id, UserResource user);
    void deleteUser(Long id);

    List<UserResource> getAllUsers();
}
