package com.example.schedulehub.service;

import com.example.schedulehub.dto.SignUpRequestDto;
import com.example.schedulehub.dto.UserResponseDto;
import com.example.schedulehub.entity.User;
import com.example.schedulehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserResponseDto scheduleHubSignUp(SignUpRequestDto signUpRequestDto) {

        User user = new User(signUpRequestDto.getUserName(), signUpRequestDto.getEmail());

        User saveUser = userRepository.save(user);

        return new UserResponseDto(saveUser.getUserId(), saveUser.getUserName(), saveUser.getEmail());
    }

    @Override
    public List<UserResponseDto> findAllUser() {

        return userRepository.findAll().stream().map(UserResponseDto::toUserResponseDto).toList();
    }
}
