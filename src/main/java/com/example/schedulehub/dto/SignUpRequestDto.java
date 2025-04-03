package com.example.schedulehub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequestDto {

    @NotBlank(message = "이름을 입력해주세요")
    private String userName;

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "이메일을 입력해주세요")
    private String password;
}
