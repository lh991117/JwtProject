package com.example.jwtproject.common.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
