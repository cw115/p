package com.example.demo.dto;

public class TimeSlotRequest {
    private String nickname; // Long userId 대신 String nickname 사용
    private int dayOfWeek;
    private String startTime; // "HH:mm:ss"
    private String endTime;   // "HH:mm:ss"

    public TimeSlotRequest() {}

    // Getter/Setter for nickname
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}