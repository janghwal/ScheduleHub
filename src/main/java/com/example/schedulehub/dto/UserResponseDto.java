package com.example.schedulehub.dto;

import com.example.schedulehub.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponseDto {

    private Long id;

    private String userName;

    private String email;

    public static UserResponseDto toUserResponseDto(User user){
        return new UserResponseDto(user.getUserId(), user.getUserName(), user.getEmail());
    }
}
