package com.example.jwtproject.common.user.service;

import com.example.jwtproject.common.user.dto.response.RoleResponse;
import com.example.jwtproject.common.user.dto.response.UserAdminResponse;
import com.example.jwtproject.common.user.entity.Role;
import com.example.jwtproject.common.user.entity.User;
import com.example.jwtproject.common.user.enums.UserRole;
import com.example.jwtproject.common.user.repository.UserRepository;
import com.example.jwtproject.global.exception.InvalidRequestStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserAdminResponse addRole(Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(()->new InvalidRequestStateException("USER_NOT_FOUND", "해당 사용자가 존재하지 않습니다."));

        user.getRoles().clear();

        user.getRoles().add(new Role(UserRole.ADMIN, user));

        List<RoleResponse> roleResponses = user.getRoles().stream()
                .map(role -> new RoleResponse(role.getUserRole().name()))
                .toList();

        return new UserAdminResponse(
                user.getUsername(),
                user.getNickname(),
                roleResponses
        );
    }
}
