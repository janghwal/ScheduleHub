package com.example.schedulehub.controller;

import com.example.schedulehub.dto.ScheduleRequestDto;
import com.example.schedulehub.dto.ScheduleResponseDto;
import com.example.schedulehub.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor // 생성자 주입
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

//    일정 생성 로그인 후 진행 가능
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto, HttpServletRequest httpRequest){

        HttpSession session = httpRequest.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");

        scheduleRequestDto.setUserId(userId);

        ScheduleResponseDto scheduleResponseDto = scheduleService.createSchedule(scheduleRequestDto);

        return new ResponseEntity<>(scheduleResponseDto ,HttpStatus.CREATED);
    }

//    모든 등록된 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule(){

        List<ScheduleResponseDto> allSchedule = scheduleService.findAllSchedule();

        return new ResponseEntity<>(allSchedule, HttpStatus.OK);
    }

//    스케줄 하나 조회 식별자 ID(scheduleId) 사용
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long scheduleId){

        ScheduleResponseDto findScheduleById = scheduleService.findScheduleById(scheduleId);

        return new ResponseEntity<>(findScheduleById, HttpStatus.OK);
    }

//    일정 수정 로그인 후 진행 가능 식별자 ID(scheduleId)사용
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequestDto scheduleRequestDto,
            HttpServletRequest httpRequest
    ){

        HttpSession session = httpRequest.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");

        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(scheduleId, userId, scheduleRequestDto);

        return new ResponseEntity<>(scheduleResponseDto , HttpStatus.OK);
    }

//    일정 수정 로그인 후 진행 가능 식별자 ID(scheduleId)사용
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            HttpServletRequest httpRequest
    ){

        HttpSession session = httpRequest.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");

        scheduleService.deleteSchedule(scheduleId, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
