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

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){

        ScheduleResponseDto findScheduleById = scheduleService.findScheduleById(id);

        return new ResponseEntity<>(findScheduleById, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto scheduleRequestDto){

        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(id, scheduleRequestDto);

        return new ResponseEntity<>(scheduleResponseDto , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){
        scheduleService.deleteSchedule(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
