package com.example.schedulehub.dto;

import com.example.schedulehub.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

//유저 관련 응답 정보를 담는 DTO
@AllArgsConstructor
@Getter
public class UserResponseDto {

    private Long userId;

    private String userName;

    private String email;

    public UserResponseDto(User user){
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
    }

    public static UserResponseDto toUserResponseDto(User user){
        return new UserResponseDto(user.getUserId(), user.getUserName(), user.getEmail());
    }
}
