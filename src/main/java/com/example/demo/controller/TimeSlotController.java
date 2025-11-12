package com.example.demo.controller;

import com.example.demo.dto.TimeSlotRequest;
import com.example.demo.entity.TimeSlot;
import com.example.demo.service.TimeSlotService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/time-slots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    /** 시간대 등록 (닉네임 사용) */
    @PostMapping
    public Map<String, Object> save(@RequestBody TimeSlotRequest req) {
        timeSlotService.saveTimeSlot(req.getNickname(), req.getDayOfWeek(), 
                req.getStartTime(), req.getEndTime());
        return Map.of("ok", true);
    }

    /** 특정 사용자 시간대 전체 조회 (닉네임 사용) */
    @GetMapping
    public List<TimeSlot> getUserSlots(@RequestParam("nickname") String nickname) { // userId 대신 nickname 파라미터 사용
        return timeSlotService.getUserTimeSlots(nickname); // 닉네임 전달
    }
}