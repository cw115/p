package com.example.demo.dto;

import java.time.LocalDateTime;

public class ScheduleRequest {
    private Long projectId;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    // Getter/Setter
    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

}
