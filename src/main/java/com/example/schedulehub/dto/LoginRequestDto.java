package com.example.schedulehub.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀 번호를 입력해주세요")
    private String password;

}
