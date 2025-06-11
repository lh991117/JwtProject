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

    public AuthUser(Long id, String username, List<UserRole> userRoles) {
        this.id = id;
        this.username = username;
        this.authorities= userRoles.stream()
                .map(userRole -> new SimpleGrantedAuthority("ROLE_" + userRole.name()))
                .toList();
    }
}
