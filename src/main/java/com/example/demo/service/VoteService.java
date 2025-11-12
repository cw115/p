package com.example.demo.service;

import com.example.demo.dto.VoteRequest;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public VoteService(VoteRepository voteRepository, PostRepository postRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Vote vote(Long postId, VoteRequest dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        if (post.getType() != Post.Type.debate)
            throw new IllegalStateException("이 게시글은 찬반 투표가 불가능합니다.");

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));

        if (voteRepository.findByPostAndUser(post, user).isPresent())
            throw new IllegalArgumentException("이미 투표했습니다.");

        Vote vote = new Vote(post, user, dto.getVoteType());
        return voteRepository.save(vote);
    }
}
