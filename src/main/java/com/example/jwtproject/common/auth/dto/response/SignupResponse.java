package com.example.jwtproject.common.auth.dto.response;

import lombok.Getter;

@Getter
public class SignupResponse {

    private final String username;
    private final String nickname;
    private final String role;

    public SignupResponse(String username, String nickname, String role) {
        this.username = username;
        this.nickname = nickname;
        this.role = role;
    }
}
