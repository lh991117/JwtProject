package com.example.jwtproject.user;

import com.example.jwtproject.common.auth.dto.request.LoginRequest;
import com.example.jwtproject.common.user.entity.Role;
import com.example.jwtproject.common.user.entity.User;
import com.example.jwtproject.common.user.enums.UserRole;
import com.example.jwtproject.common.user.repository.UserRepository;
import com.example.jwtproject.global.config.JwtUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AdminAuthorizationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String adminToken;
    private String userToken;
    private Long targetUserId;

    @BeforeEach
    void setup() throws Exception {
        userRepository.deleteAll();

        // 대상 유저
        User targetUser = new User("target", passwordEncoder.encode("pass1234"), List.of(UserRole.USER), "타겟");
        userRepository.save(targetUser);
        targetUserId = targetUser.getId();

        // 일반 사용자
        User user = new User("user", passwordEncoder.encode("pass1234"), List.of(UserRole.USER), "유저");
        userRepository.save(user);
        userToken = loginAndGetToken("user", "pass1234");

        // 관리자 사용자
        User admin = new User("admin", passwordEncoder.encode("pass1234"), List.of(UserRole.ADMIN), "관리자");
        userRepository.save(admin);
        adminToken = loginAndGetToken("admin", "pass1234");
    }

    @Test
    void 관리자_권한_부여_성공() throws Exception {
        mockMvc.perform(patch("/admin/users/" + targetUserId + "/roles")
                        .header("Authorization", adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("target"))
                .andExpect(jsonPath("$.roles[0].role").value("ADMIN"));
    }

    @Test
    void 일반유저가_권한_부여_시도시_실패() throws Exception {
        mockMvc.perform(patch("/admin/users/" + targetUserId + "/roles")
                        .header("Authorization", userToken))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.error.code").value("ACCESS_DENIED"));
    }

    @Test
    void 존재하지_않는_유저에게_권한_부여시_실패() throws Exception {
        mockMvc.perform(patch("/admin/users/9999999/roles")
                        .header("Authorization", adminToken))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.code").value("USER_NOT_FOUND"));
    }

    private String loginAndGetToken(String username, String password) throws Exception {
        LoginRequest request = new LoginRequest(username, password);
        String content = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode node = objectMapper.readTree(content);
        return node.get("bearerToken").asText();
    }
}
