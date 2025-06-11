package com.example.jwtproject.common.user.entity;

import com.example.jwtproject.common.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    private String nickname;

    public void addRole(UserRole role){
        this.roles.add((new Role(role, this)));
    }

    public void changeRoles(List<UserRole> newRoles){
        this.roles.clear();
        for(UserRole role : newRoles){
            this.addRole(role);
        }
    }

    public User(String username, String password, List<UserRole> userRoles, String nickname) {
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>();
        for(UserRole role : userRoles){
            this.roles.add((new Role(role, this)));
        }
        this.nickname = nickname;
    }
}
