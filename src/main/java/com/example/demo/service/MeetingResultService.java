package com.example.demo.service;

import com.example.demo.dto.MeetingResultRequest;
import com.example.demo.entity.MeetingResult;
import com.example.demo.entity.Schedule;
import com.example.demo.repository.MeetingResultRepository;
import com.example.demo.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingResultService {

    private final MeetingResultRepository meetingResultRepository;
    private final ScheduleRepository scheduleRepository;

    public MeetingResultService(MeetingResultRepository meetingResultRepository, ScheduleRepository scheduleRepository) {
        this.meetingResultRepository = meetingResultRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public MeetingResult addResult(MeetingResultRequest request) {
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        MeetingResult result = new MeetingResult(schedule, request.getContent());
        return meetingResultRepository.save(result);
    }

    public List<MeetingResult> getResults(Long scheduleId) {
        return meetingResultRepository.findBySchedule_ScheduleIdOrderByCreatedAtAsc(scheduleId);
    }

    @Transactional
    public MeetingResult updateResult(Long resultId, String newContent) {
        MeetingResult result = meetingResultRepository.findById(resultId)
                .orElseThrow(() -> new IllegalArgumentException("회의 결과를 찾을 수 없습니다."));
        result.setContent(newContent);
        return result;
    }

    @Transactional
    public void deleteResult(Long resultId) {
        meetingResultRepository.deleteById(resultId);
    }
}
