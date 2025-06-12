package com.example.jwtproject.global.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private ErrorBody errorBody;
}
