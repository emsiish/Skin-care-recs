package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.dto.LoginDto;
import com.example.skincarerecs.controller.dto.TokenDto;
import com.example.skincarerecs.controller.dto.UserDto;

public interface AuthenticationService {
    TokenDto register(UserDto request);
    TokenDto login(LoginDto request);
}
