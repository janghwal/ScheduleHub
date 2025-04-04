package com.example.schedulehub.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

//로그인 요청을 위한 이메일과 비밀번호 담는 DTO
@Getter
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀 번호를 입력해주세요")
    private String password;

}
