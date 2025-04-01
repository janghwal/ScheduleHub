package com.example.schedulehub.service;

import com.example.schedulehub.dto.SignUpRequestDto;
import com.example.schedulehub.dto.SignUpResponseDto;

public interface UserService {

    SignUpResponseDto scheduleHubSignUp(SignUpRequestDto signUpRequestDto);
}
