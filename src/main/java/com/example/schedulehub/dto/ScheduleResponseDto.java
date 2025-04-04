package com.example.schedulehub.dto;

import com.example.schedulehub.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

//스케줄 관련 응답 정보를 담는 DTO
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
