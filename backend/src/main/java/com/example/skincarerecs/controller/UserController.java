package com.example.skincarerecs.controller;

import com.example.skincarerecs.controller.dto.TagDto;
import com.example.skincarerecs.controller.dto.UserDto;
import com.example.skincarerecs.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto addUser(@RequestBody UserDto user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "/{id}/tags")
    public UserDto updateUser(@PathVariable Long id, @RequestBody List<TagDto> tags) {
        System.out.println("tags: " + tags);
        return userService.updateUserTags(id, tags);
    }
}
