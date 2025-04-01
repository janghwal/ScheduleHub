package com.example.schedulehub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignUpResponseDto {

    private Long id;

    private String userName;

    private String email;
}
