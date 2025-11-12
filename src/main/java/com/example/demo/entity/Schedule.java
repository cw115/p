package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String title;

    private LocalDateTime startTime;
    private LocalDateTime endTime;


    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MeetingResult> results = new ArrayList<>();

    public Schedule() {}

    public Schedule(Project project, String title, LocalDateTime startTime, LocalDateTime endTime) {
        this.project = project;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    // Getter/Setter
    public Long getScheduleId() { return scheduleId; }
    public String getTitle() { return title; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }

    public List<MeetingResult> getResults() { return results; }

    public void setTitle(String title) { this.title = title; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

}
