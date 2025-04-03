package com.example.schedulehub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    @Setter
    private Long userId;

    private String title;

    private String contents;
}
