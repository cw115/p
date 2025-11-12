package com.example.demo.repository;

import com.example.demo.entity.MeetingResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MeetingResultRepository extends JpaRepository<MeetingResult, Long> {
    List<MeetingResult> findBySchedule_ScheduleIdOrderByCreatedAtAsc(Long scheduleId);
}
