package com.example.demo.dto;

import java.time.LocalDateTime;

public class CommentResponse {
    private Long id;
    private String content;
    private String authorNickname;
    private LocalDateTime createdAt;

    public CommentResponse(Long id, String content, String authorNickname, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.authorNickname = authorNickname;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getContent() { return content; }
    public String getAuthorNickname() { return authorNickname; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
