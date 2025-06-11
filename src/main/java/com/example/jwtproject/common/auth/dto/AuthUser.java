package com.example.jwtproject.common.auth.dto;

import com.example.jwtproject.common.user.enums.UserRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class AuthUser {

    private final Long id;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthUser(Long id, String username, UserRole userRole) {
        this.id = id;
        this.username = username;
        this.authorities= List.of(new SimpleGrantedAuthority(userRole.name()));
    }
}
