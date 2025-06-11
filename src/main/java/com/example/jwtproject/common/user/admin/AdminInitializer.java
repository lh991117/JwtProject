package com.example.jwtproject.common.user.admin;

import com.example.jwtproject.common.user.dto.response.RoleResponse;
import com.example.jwtproject.common.user.entity.User;
import com.example.jwtproject.common.user.enums.UserRole;
import com.example.jwtproject.common.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if(!userRepository.existsByUsername("admin")) {
            User adminUser = new User(
                    "admin",
                    passwordEncoder.encode("admin1234"),
                    List.of(UserRole.ADMIN),
                    "관리자"
            );

            userRepository.save(adminUser);
        }
    }
}
