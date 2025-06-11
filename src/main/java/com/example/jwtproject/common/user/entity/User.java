package com.example.jwtproject.common.user.entity;

import com.example.jwtproject.common.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String nickname;

    public User(String username, String password, UserRole userRole, String nickname) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.nickname = nickname;
    }
}
