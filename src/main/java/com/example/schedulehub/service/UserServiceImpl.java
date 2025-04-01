package com.example.schedulehub.service;

import com.example.schedulehub.dto.SignUpRequestDto;
import com.example.schedulehub.dto.SignUpResponseDto;
import com.example.schedulehub.entity.User;
import com.example.schedulehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public SignUpResponseDto scheduleHubSignUp(SignUpRequestDto signUpRequestDto) {

        User user = new User(signUpRequestDto.getUserName(), signUpRequestDto.getEmail());

        User saveUser = userRepository.save(user);

        return new SignUpResponseDto(saveUser.getUserId(), saveUser.getUserName(), saveUser.getEmail());
    }
}
