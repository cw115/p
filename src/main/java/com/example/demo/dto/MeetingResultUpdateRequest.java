package com.example.demo.dto;

// 클라이언트가 PUT 요청 시 보낸 JSON 본문(Request Body)을 담는 DTO
public class MeetingResultUpdateRequest {
    private String content;

    // 기본 생성자 (필수)
    public MeetingResultUpdateRequest() {
    }

    // Getter
    public String getContent() {
        return content;
    }

    // Setter (JSON 필드를 매핑하기 위해 필요)
    public void setContent(String content) {
        this.content = content;
    }
}