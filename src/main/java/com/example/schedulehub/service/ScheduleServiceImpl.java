package com.example.schedulehub.service;

import com.example.schedulehub.dto.ScheduleRequestDto;
import com.example.schedulehub.dto.ScheduleResponseDto;
import com.example.schedulehub.entity.Schedule;
import com.example.schedulehub.entity.User;
import com.example.schedulehub.repository.ScheduleRepository;
import com.example.schedulehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    private final UserRepository userRepository;

    @Transactional
    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {

        User findUser = userRepository.findUserByEmailOrElseThrow(scheduleRequestDto.getEmail());

        Schedule schedule = new Schedule(findUser.getUserName(), scheduleRequestDto.getTitle(), scheduleRequestDto.getContents());
        schedule.setUser(findUser);

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

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {

        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        findSchedule.updateSchedule(scheduleRequestDto.getTitle(), scheduleRequestDto.getContents());

        return ScheduleResponseDto.toScheduleResponseDto(findSchedule);
    }

    @Override
    public void deleteSchedule(Long id) {

        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);
    }
}
