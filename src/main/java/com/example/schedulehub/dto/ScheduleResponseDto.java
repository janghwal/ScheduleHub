package com.example.schedulehub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private String userName;

    private String title;

    private String contents;
}
