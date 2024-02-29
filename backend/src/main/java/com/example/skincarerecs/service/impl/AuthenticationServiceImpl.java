package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.config.JwtService;
import com.example.skincarerecs.controller.dto.LoginDto;
import com.example.skincarerecs.controller.dto.TokenDto;
import com.example.skincarerecs.controller.dto.UserDto;
import com.example.skincarerecs.entity.Role;
import com.example.skincarerecs.entity.User;
import com.example.skincarerecs.repository.UserRepository;
import com.example.skincarerecs.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public TokenDto register(UserDto request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);
        String jwtToken = jwtService.generateToken(user.getId(), user);

        return TokenDto.builder()
                .token(jwtToken)
                .build();
    }

    public TokenDto login(LoginDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User client = repository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(client.getId(), client);
        return TokenDto.builder()
                .token(jwtToken)
                .build();
    }
}