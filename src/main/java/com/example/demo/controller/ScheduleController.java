package com.example.demo.controller;

import com.example.demo.dto.ScheduleRequest;
import com.example.demo.entity.Schedule;
import com.example.demo.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/add")
    public ResponseEntity<Schedule> addSchedule(@RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(scheduleService.addSchedule(request));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Schedule>> getSchedulesByProject(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(scheduleService.getSchedulesByProject(projectId));
    }
}
