package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes",
       uniqueConstraints = @UniqueConstraint(name = "unique_vote", columnNames = {"post_id", "user_id"}))
public class Vote {

    public enum VoteType { 찬성, 반대 }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "vote_type", nullable = false, length = 10)
    private VoteType voteType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Vote() {}

    public Vote(Post post, User user, VoteType voteType) {
        this.post = post;
        this.user = user;
        this.voteType = voteType;
    }

    // Getters
    public Long getId() { return id; }
    public Post getPost() { return post; }
    public User getUser() { return user; }
    public VoteType getVoteType() { return voteType; }
}
