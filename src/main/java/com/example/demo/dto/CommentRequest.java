package com.example.demo.dto;

public class CommentRequest {
    private String nickname; // userId 대신 닉네임 사용
    private String content;

    public CommentRequest() {}

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
