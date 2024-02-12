package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.dto.LoginDto;
import com.example.skincarerecs.controller.dto.TokenDto;
import com.example.skincarerecs.controller.dto.UserDto;

public interface AuthenticationService {
    public TokenDto register(UserDto request);
    public TokenDto login(LoginDto request);
}
