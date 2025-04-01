package com.example.schedulehub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    // 추후에 이메일 형식 검증 필요
    private String email;

    private String title;

    private String contents;
}
