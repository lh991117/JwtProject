package com.example.jwtproject.common.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotNull
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&]).{8,}$"
            , message = "대문자, 숫자, 특수문자(!,@,#,$,%,^,&)를 최소 1개 이상 포함한 8자리 이상으로 입력해주세요.")
    private String password;

    @NotBlank
    private String nickname;
}
