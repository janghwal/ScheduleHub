package com.example.schedulehub.service;

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

        User user = new User(signUpRequestDto.getUserName(), signUpRequestDto.getEmail());

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
    public UserResponseDto findUserById(Long id) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        return new UserResponseDto(findUser);
    }

    @Override
    public UserResponseDto findUserByEmail(String email) {

        User findUser = userRepository.findUserByEmailOrElseThrow(email);

        return new UserResponseDto(findUser);
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        if(userRequestDto.getUserName() == null && userRequestDto.getEmail() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "변경 할 값이 없습니다.");
        }else if(userRequestDto.getUserName() == null){
            findUser.setEmail(userRequestDto.getEmail());
        }else if(userRequestDto.getEmail() == null){
            findUser.setUserName(userRequestDto.getUserName());
        }else{
            findUser.setUserName(userRequestDto.getUserName());
            findUser.setEmail(userRequestDto.getEmail());
        }

        return new UserResponseDto(findUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findUserByIdOrElseThrow(id);

        userRepository.delete(user);
    }
}
