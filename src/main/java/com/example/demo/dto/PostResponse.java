package com.example.demo.dto;

import java.time.LocalDateTime;

public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String postType; 
    private LocalDateTime createdAt;
    private long votesFor;     
    private long votesAgainst; 

    public PostResponse(Long id, String title, String content, String author, String postType, LocalDateTime createdAt, long votesFor, long votesAgainst) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.postType = postType;
        this.createdAt = createdAt;
        this.votesFor = votesFor;
        this.votesAgainst = votesAgainst; 
    }

    // Getter
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
    public String getPostType() { return postType; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public long getVotesFor() { return votesFor; }
    public long getVotesAgainst() { return votesAgainst; }
}