package com.example.demo.dto;

import com.example.demo.entity.Post;

public class PostRequest {
    private Long projectId;
    private String nickname; 
    private String title;
    private String content;
    private Post.Type type; // 수정 시 type 변경은 현재 미지원

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public String getNickname() { return nickname; } 
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Post.Type getType() { return type; }
    public void setType(Post.Type type) { this.type = type; }
}