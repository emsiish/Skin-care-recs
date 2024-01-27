package com.example.skincarerecs.controller;

import com.example.skincarerecs.controller.resources.UserResource;
import com.example.skincarerecs.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResource addUser(@RequestBody UserResource user) {
        return userService.addUser(user);
    }

    @GetMapping(path = "/{id}")
    public UserResource getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
