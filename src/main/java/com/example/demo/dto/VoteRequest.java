package com.example.demo.dto;

import com.example.demo.entity.Vote;

public class VoteRequest {
    private Long userId;
    private Vote.VoteType voteType;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Vote.VoteType getVoteType() { return voteType; }
    public void setVoteType(Vote.VoteType voteType) { this.voteType = voteType; }
}
