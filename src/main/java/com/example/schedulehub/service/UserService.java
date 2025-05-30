package com.example.schedulehub.service;

import com.example.schedulehub.dto.LoginRequestDto;
import com.example.schedulehub.dto.SignUpRequestDto;
import com.example.schedulehub.dto.UserRequestDto;
import com.example.schedulehub.dto.UserResponseDto;
import com.example.schedulehub.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponseDto scheduleHubSignUp(SignUpRequestDto signUpRequestDto);

    List<UserResponseDto> findAllUser();

    UserResponseDto findUserById(Long userId);

    UserResponseDto findUserByEmail(String email);

    UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto);

    void deleteUser(Long userId);

    Optional<User> login(LoginRequestDto loginRequestDto);
}
