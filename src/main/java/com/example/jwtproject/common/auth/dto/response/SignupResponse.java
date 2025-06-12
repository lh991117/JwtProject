package com.example.jwtproject.common.auth.dto.response;

import com.example.jwtproject.common.user.dto.response.RoleResponse;
import com.example.jwtproject.common.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
public class SignupResponse {

    private String username;
    private String nickname;
    private List<RoleResponse> roles;
}
