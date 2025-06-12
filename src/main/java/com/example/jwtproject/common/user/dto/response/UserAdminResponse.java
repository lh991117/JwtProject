package com.example.jwtproject.common.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserAdminResponse {

    private String username;
    private String nickname;
    private List<RoleResponse> roles;
}
