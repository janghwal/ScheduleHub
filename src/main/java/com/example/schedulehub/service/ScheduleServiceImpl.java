package com.example.schedulehub.service;

import com.example.schedulehub.dto.ScheduleRequestDto;
import com.example.schedulehub.dto.ScheduleResponseDto;
import com.example.schedulehub.entity.Schedule;
import com.example.schedulehub.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule = new Schedule(scheduleRequestDto.getUserName(), scheduleRequestDto.getTitle(), scheduleRequestDto.getContents());

        Schedule saveSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(saveSchedule.getScheduleId(), saveSchedule.getUserName(), saveSchedule.getTitle(), saveSchedule.getContents());
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {

        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toScheduleResponseDto).toList();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule.getScheduleId(), findSchedule.getUserName(), findSchedule.getTitle(), findSchedule.getContents());
    }
}
