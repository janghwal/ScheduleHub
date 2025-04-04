package com.example.schedulehub.service;

import com.example.schedulehub.dto.ScheduleRequestDto;
import com.example.schedulehub.dto.ScheduleResponseDto;
import com.example.schedulehub.entity.Schedule;
import com.example.schedulehub.entity.User;
import com.example.schedulehub.repository.ScheduleRepository;
import com.example.schedulehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    private final UserRepository userRepository;

    // 인증 필요
    @Transactional
    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {

        User findUser = userRepository.findUserByIdOrElseThrow(scheduleRequestDto.getUserId());

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
    public ScheduleResponseDto findScheduleById(Long scheduleId) {

        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        return new ScheduleResponseDto(findSchedule.getScheduleId(), findSchedule.getUserName(), findSchedule.getTitle(), findSchedule.getContents());
    }

    // 인증 필요
    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long scheduleId, Long userId, ScheduleRequestDto scheduleRequestDto) {

        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        if(!Objects.equals(findSchedule.getUser().getUserId(), userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        if(scheduleRequestDto.getTitle() == null && scheduleRequestDto.getContents() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "변경 할 값이 없습니다.");
        }

        if(scheduleRequestDto.getTitle() != null){
            findSchedule.setTitle(scheduleRequestDto.getTitle());
        }

        if(scheduleRequestDto.getContents() != null){
            findSchedule.setContents(scheduleRequestDto.getContents());
        }

        return ScheduleResponseDto.toScheduleResponseDto(findSchedule);
    }

    // 인증 필요
    @Override
    public void deleteSchedule(Long scheduleId, Long userId) {

        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        if(!Objects.equals(findSchedule.getUser().getUserId(), userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        scheduleRepository.delete(findSchedule);
    }

}
