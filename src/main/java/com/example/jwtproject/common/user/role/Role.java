package com.example.jwtproject.common.user.role;

import com.example.jwtproject.common.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Role {

    private UserRole userRole;
}
