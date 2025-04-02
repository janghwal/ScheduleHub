package com.example.schedulehub.controller;


import com.example.schedulehub.dto.SignUpRequestDto;
import com.example.schedulehub.dto.UserRequestDto;
import com.example.schedulehub.dto.UserResponseDto;
import com.example.schedulehub.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> scheduleHubSignUp(@RequestBody SignUpRequestDto signUpRequestDto){

        UserResponseDto userResponseDto = userService.scheduleHubSignUp(signUpRequestDto);

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUser(){

        List<UserResponseDto> findAllUser = userService.findAllUser();

        return new ResponseEntity<>(findAllUser, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id){

        UserResponseDto findUser = userService.findUserById(id);

        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<UserResponseDto> findUserByEmail(@RequestParam String email){

        UserResponseDto findUser = userService.findUserByEmail(email);

        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto userRequestDto
            ){

        UserResponseDto userResponseDto = userService.updateUser(id, userRequestDto);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }



}
