package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.resources.UserResource;

public interface UserService {
    UserResource addUser(UserResource user);
    UserResource getUserById(Long id);
    UserResource updateUser(Long id, UserResource user);
    void deleteUser(Long id);
}
