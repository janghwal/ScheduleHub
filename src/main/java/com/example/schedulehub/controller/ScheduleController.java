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

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto, HttpServletRequest httpRequest){

        HttpSession session = httpRequest.getSession(false);
        Long userId = (Long) session.getAttribute("sessionKey");

        scheduleRequestDto.setUserId(userId);

        ScheduleResponseDto scheduleResponseDto = scheduleService.createSchedule(scheduleRequestDto);

        return new ResponseEntity<>(scheduleResponseDto ,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule(){

        List<ScheduleResponseDto> allSchedule = scheduleService.findAllSchedule();

        return new ResponseEntity<>(allSchedule, HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long scheduleId){

        ScheduleResponseDto findScheduleById = scheduleService.findScheduleById(scheduleId);

        return new ResponseEntity<>(findScheduleById, HttpStatus.OK);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequestDto scheduleRequestDto,
            HttpServletRequest httpRequest
    ){

        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(scheduleId, scheduleRequestDto);

        return new ResponseEntity<>(scheduleResponseDto , HttpStatus.OK);
    }

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
