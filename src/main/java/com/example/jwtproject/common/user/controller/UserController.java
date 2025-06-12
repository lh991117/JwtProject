package com.example.jwtproject.common.user.controller;

import com.example.jwtproject.common.auth.dto.response.SignupResponse;
import com.example.jwtproject.common.user.dto.response.RoleResponse;
import com.example.jwtproject.common.user.dto.response.UserAdminResponse;
import com.example.jwtproject.common.user.entity.User;
import com.example.jwtproject.common.user.enums.UserRole;
import com.example.jwtproject.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/admin/users/{userId}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public UserAdminResponse addAdminRole(
            @PathVariable Long userId
    ){
        return userService.addRole(userId);
    }
}
