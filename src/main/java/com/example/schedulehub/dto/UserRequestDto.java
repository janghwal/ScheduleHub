package com.example.schedulehub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {

    private Long Id;

    private String userName;

    private String email;
}
