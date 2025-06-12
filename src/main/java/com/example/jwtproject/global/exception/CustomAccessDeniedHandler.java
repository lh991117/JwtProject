package com.example.jwtproject.global.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String body = """
                {
                    "error": {
                      "code": "ACCESS_DENIED",
                      "message": "관리자 권한이 필요한 요청입니다. 접근 권한이 없습니다."
                    }
                }
                """;

        response.getWriter().write(body);
    }
}
