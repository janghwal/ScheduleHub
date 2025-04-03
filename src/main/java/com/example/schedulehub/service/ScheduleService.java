package com.example.schedulehub.service;

import com.example.schedulehub.dto.ScheduleRequestDto;
import com.example.schedulehub.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto);

    List<ScheduleResponseDto> findAllSchedule();

    ScheduleResponseDto findScheduleById(Long scheduleId);

    ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleRequestDto scheduleRequestDto);

    void deleteSchedule(Long scheduleId, Long userId);
}
