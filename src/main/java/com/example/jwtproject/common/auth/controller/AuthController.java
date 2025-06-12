package com.example.jwtproject.common.auth.controller;

import com.example.jwtproject.common.auth.dto.request.LoginRequest;
import com.example.jwtproject.common.auth.dto.request.SignupRequest;
import com.example.jwtproject.common.auth.dto.response.LoginResponse;
import com.example.jwtproject.common.auth.dto.response.SignupResponse;
import com.example.jwtproject.common.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public SignupResponse signup(@RequestBody SignupRequest signupRequest) {
        return authService.signup(signupRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
