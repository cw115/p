package com.example.demo.dto;

public class MeetingResultRequest {
    private Long scheduleId;
    private String content;

    // Getter/Setter
    public Long getScheduleId() { return scheduleId; }
    public void setScheduleId(Long scheduleId) { this.scheduleId = scheduleId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
