package com.example.schedulehub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    private String userName;

    private String title;

    private String contents;
}
