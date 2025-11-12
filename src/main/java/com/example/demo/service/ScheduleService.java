package com.example.demo.service;

import com.example.demo.dto.ScheduleRequest;
import com.example.demo.entity.Project;
import com.example.demo.entity.Schedule;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ProjectRepository projectRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, ProjectRepository projectRepository) {
        this.scheduleRepository = scheduleRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional
    public Schedule addSchedule(ScheduleRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("프로젝트가 존재하지 않습니다."));
        Schedule schedule = new Schedule(
                project,
                request.getTitle(),
                request.getStartTime(),
                request.getEndTime()
        );
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getSchedulesByProject(Long projectId) {
        return scheduleRepository.findByProject_ProjectId(projectId);
    }
}
