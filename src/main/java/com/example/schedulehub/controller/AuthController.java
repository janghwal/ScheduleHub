package com.example.schedulehub.controller;

import com.example.schedulehub.dto.LoginRequestDto;
import com.example.schedulehub.entity.User;
import com.example.schedulehub.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequiredArgsConstructor // 생성자 주입
@RequestMapping("/login")
public class AuthController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest httpRequest){

        Optional<User> loginUser = userService.login(loginRequestDto);

        if(loginUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        HttpSession session = httpRequest.getSession();
        session.setAttribute("sessionKey", loginUser.get().getUserId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpRequest, HttpServletResponse httpResponse){

        HttpSession session = httpRequest.getSession(false);

        if(session != null){
            session.invalidate();
        }

        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        httpResponse.addCookie(cookie);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
