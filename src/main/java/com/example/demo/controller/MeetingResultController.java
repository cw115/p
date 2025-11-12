package com.example.demo.controller;

import com.example.demo.dto.MeetingResultRequest;
import com.example.demo.dto.MeetingResultUpdateRequest; // 👈 새로 추가된 DTO import
import com.example.demo.entity.MeetingResult;
import com.example.demo.service.MeetingResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class MeetingResultController {

    private final MeetingResultService meetingResultService;

    public MeetingResultController(MeetingResultService meetingResultService) {
        this.meetingResultService = meetingResultService;
    }

    @PostMapping
    public ResponseEntity<MeetingResult> addResult(@RequestBody MeetingResultRequest request) {
        return ResponseEntity.ok(meetingResultService.addResult(request));
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<List<MeetingResult>> getResults(@PathVariable("scheduleId") Long scheduleId) {
        return ResponseEntity.ok(meetingResultService.getResults(scheduleId));
    }

    @PutMapping("/{resultId}")
    public ResponseEntity<MeetingResult> updateResult(
            @PathVariable("resultId") Long resultId,
            @RequestBody MeetingResultUpdateRequest request) { // 👈 DTO를 사용하여 요청 본문을 객체로 받음
            
        // 👈 DTO 객체에서 순수한 content 필드만 추출하여 Service에 전달
        return ResponseEntity.ok(meetingResultService.updateResult(resultId, request.getContent())); 
    }

    @DeleteMapping("/{resultId}")
    public ResponseEntity<Void> deleteResult(@PathVariable("resultId") Long resultId) {
        meetingResultService.deleteResult(resultId);
        return ResponseEntity.noContent().build();
    }
}