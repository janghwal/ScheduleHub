package com.example.schedulehub.controller;


import com.example.schedulehub.dto.SignUpRequestDto;
import com.example.schedulehub.dto.UserRequestDto;
import com.example.schedulehub.dto.UserResponseDto;
import com.example.schedulehub.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> scheduleHubSignUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto){

        UserResponseDto userResponseDto = userService.scheduleHubSignUp(signUpRequestDto);

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUser(){

        List<UserResponseDto> findAllUser = userService.findAllUser();

        return new ResponseEntity<>(findAllUser, HttpStatus.OK);
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long userId){

        UserResponseDto findUser = userService.findUserById(userId);

        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<UserResponseDto> findUserByEmail(@Email(message = "이메일 형식을 입력하세요") @RequestParam String email){

        UserResponseDto findUser = userService.findUserByEmail(email);

        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UserResponseDto> updateUser(
            @Valid @RequestBody UserRequestDto userRequestDto,
            HttpServletRequest httpRequest
            ){

        HttpSession session = httpRequest.getSession(false);

        Long userId = (Long) session.getAttribute("sessionKey");

        log.info(String.valueOf(userId));

        UserResponseDto userResponseDto = userService.updateUser(userId, userRequestDto);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(HttpServletRequest httpRequest, HttpServletResponse httpResponse){

        HttpSession session = httpRequest.getSession(false);

        Long userId = (Long) session.getAttribute("sessionKey");

        userService.deleteUser(userId);

        session.invalidate();

        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);

        httpResponse.addCookie(cookie);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
