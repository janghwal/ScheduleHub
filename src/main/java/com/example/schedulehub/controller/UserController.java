package com.example.schedulehub.controller;


import com.example.schedulehub.dto.SignUpRequestDto;
import com.example.schedulehub.dto.UserResponseDto;
import com.example.schedulehub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<UserResponseDto> findUserById(){


        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> findUserByEmail(@RequestParam String email){



        return new ResponseEntity<>( HttpStatus.OK);
    }





}
