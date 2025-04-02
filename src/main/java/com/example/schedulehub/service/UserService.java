package com.example.schedulehub.service;

import com.example.schedulehub.dto.SignUpRequestDto;
import com.example.schedulehub.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto scheduleHubSignUp(SignUpRequestDto signUpRequestDto);

    List<UserResponseDto> findAllUser();
}
