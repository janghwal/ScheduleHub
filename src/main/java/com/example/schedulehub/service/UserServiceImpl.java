package com.example.schedulehub.service;

import com.example.schedulehub.dto.LoginRequestDto;
import com.example.schedulehub.dto.SignUpRequestDto;
import com.example.schedulehub.dto.UserRequestDto;
import com.example.schedulehub.dto.UserResponseDto;
import com.example.schedulehub.entity.User;
import com.example.schedulehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserResponseDto scheduleHubSignUp(SignUpRequestDto signUpRequestDto) {

        User user = new User(signUpRequestDto.getUserName(), signUpRequestDto.getEmail(), signUpRequestDto.getPassword());

        Optional<User> userByEmail = userRepository.findUserByEmail(signUpRequestDto.getEmail());

        if(userByEmail.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        User saveUser = userRepository.save(user);

        return new UserResponseDto(saveUser.getUserId(), saveUser.getUserName(), saveUser.getEmail());
    }

    @Override
    public List<UserResponseDto> findAllUser() {

        return userRepository.findAll().stream().map(UserResponseDto::toUserResponseDto).toList();
    }

    @Override
    public UserResponseDto findUserById(Long userId) {

        User findUser = userRepository.findUserByIdOrElseThrow(userId);

        return new UserResponseDto(findUser);
    }

    @Override
    public UserResponseDto findUserByEmail(String email) {

        User findUser = userRepository.findUserByEmailOrElseThrow(email);

        return new UserResponseDto(findUser);
    }

    // 인증 필요
    @Override
    @Transactional
    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto) {

        User findUser = userRepository.findUserByIdOrElseThrow(userId);

        if(userRequestDto.getUserName() == null && userRequestDto.getEmail() == null && userRequestDto.getPassword() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "변경 할 값이 없습니다.");
        }

        if(userRequestDto.getEmail() != null){
            if(userRepository.findUserByEmail(userRequestDto.getEmail()).isPresent() && !findUser.getEmail().equals(userRequestDto.getEmail())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "값이 중복됩니다.");
            }
            findUser.setEmail(userRequestDto.getEmail());
        }

        if(userRequestDto.getUserName() != null){
            findUser.setUserName(userRequestDto.getUserName());
        }

        if(userRequestDto.getPassword() != null){
            findUser.setPassword(userRequestDto.getPassword());
        }

        return new UserResponseDto(findUser);
    }

    // 인증 필요
    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findUserByIdOrElseThrow(userId);

        userRepository.delete(user);
    }

    @Transactional
    @Override
    public Optional<User> login(LoginRequestDto loginRequestDto) {

        User userInfo = userRepository.findUserByEmailOrElseThrow(loginRequestDto.getEmail());

        if(!userInfo.getPassword().equals(loginRequestDto.getPassword())){
            return Optional.empty();
        }

        return Optional.of(userInfo);
    }
}
