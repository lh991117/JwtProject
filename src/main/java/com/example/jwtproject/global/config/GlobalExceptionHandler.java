package com.example.jwtproject.global.config;

import com.example.jwtproject.common.exception.AuthException;
import com.example.jwtproject.global.exception.InvalidRequestStateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestStateException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidRequestState(InvalidRequestStateException e) {
        return getWrappedErrorResponse(HttpStatus.BAD_REQUEST, e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Map<String, Object>> handleAuthException(AuthException ex) {
        return getWrappedErrorResponse(HttpStatus.UNAUTHORIZED, ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<Map<String, Object>> handleServerException(ServerException ex) {
        return getWrappedErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER_ERROR", ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("code", "ACCESS_DENIED");
        error.put("message", "관리자 권한이 필요한 요청입니다. 접근 권한이 없습니다.");

        Map<String, Object> response = new HashMap<>();
        response.put("error", error);

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<Map<String, Object>> getWrappedErrorResponse(HttpStatus status, String code, String message) {
        Map<String, String> errorBody = new HashMap<>();
        errorBody.put("code", code);
        errorBody.put("message", message);

        Map<String, Object> response = new HashMap<>();
        response.put("error", errorBody);

        return new ResponseEntity<>(response, status);
    }
}
