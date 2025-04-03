package com.example.schedulehub.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {

    private String userName;

    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    private String password;
}
