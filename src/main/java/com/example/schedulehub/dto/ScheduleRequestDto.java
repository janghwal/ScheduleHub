package com.example.schedulehub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//스케줄 관련 요청 정보를 담는 DTO
@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    @Setter
    private Long userId;

    private String title;

    private String contents;
}
