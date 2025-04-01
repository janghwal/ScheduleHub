package com.example.schedulehub.dto;

import com.example.schedulehub.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long Id;

    private String userName;

    private String title;

    private String contents;

    public static ScheduleResponseDto toScheduleResponseDto(Schedule schedule){
        return new ScheduleResponseDto(schedule.getScheduleId(), schedule.getUserName(), schedule.getTitle(), schedule.getContents());
    }
}
