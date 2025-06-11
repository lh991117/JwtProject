package com.example.jwtproject.common.user.repository;

import com.example.jwtproject.common.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
}
