package com.example.jwtproject.global.exception;

public class InvalidRequestStateException extends RuntimeException {

    private final String errorCode;

    public InvalidRequestStateException(String errorCode, String message) {

        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
