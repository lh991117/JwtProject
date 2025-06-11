package com.example.jwtproject.common.exception;

public class ServerException extends RuntimeException {

    private final String errorCode;

    public ServerException(String errorCode, String message) {

        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
