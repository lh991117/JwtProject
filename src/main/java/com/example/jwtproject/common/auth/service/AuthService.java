package com.example.jwtproject.common.auth.service;

import com.example.jwtproject.common.auth.dto.request.LoginRequest;
import com.example.jwtproject.common.auth.dto.request.SignupRequest;
import com.example.jwtproject.common.auth.dto.response.LoginResponse;
import com.example.jwtproject.common.auth.dto.response.SignupResponse;
import com.example.jwtproject.common.exception.AuthException;
import com.example.jwtproject.common.user.entity.User;
import com.example.jwtproject.common.user.enums.UserRole;
import com.example.jwtproject.common.user.repository.UserRepository;
import com.example.jwtproject.global.config.JwtUtil;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public SignupResponse signup(SignupRequest request) {

        if(userRepository.existsByUsername(request.getUsername())) {
            throw new InvalidRequestStateException("이미 가입된 사용자입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserRole userRole = UserRole.USER;

        User user = new User(
                request.getUsername(),
                encodedPassword,
                userRole,
                request.getNickname()
        );

        User savedUser = userRepository.save(user);

        return new SignupResponse(savedUser.getUsername(), savedUser.getNickname(), savedUser.getUserRole().toString());
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new InvalidRequestStateException("아이디 또는 비밀번호 올바르지 않습니다."));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        String bearerToken = jwtUtil.createToken(user.getId(), user.getUsername(), user.getUserRole());

        return new LoginResponse(bearerToken);
    }
}
