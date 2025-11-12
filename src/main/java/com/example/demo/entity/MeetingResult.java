package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "meeting_results")
public class MeetingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @Column(nullable = false, length = 2000)
    private String content;

    private LocalDateTime createdAt;

    public MeetingResult() {}

    public MeetingResult(Schedule schedule, String content) {
        this.schedule = schedule;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // Getter/Setter
    public Long getResultId() { return resultId; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setContent(String content) { this.content = content; }
}
