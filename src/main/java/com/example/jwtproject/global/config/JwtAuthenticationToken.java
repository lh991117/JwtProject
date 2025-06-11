package com.example.jwtproject.global.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final AuthUser authUser;
}
