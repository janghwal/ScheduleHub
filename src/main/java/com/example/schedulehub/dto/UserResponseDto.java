package com.example.schedulehub.dto;

import com.example.schedulehub.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
