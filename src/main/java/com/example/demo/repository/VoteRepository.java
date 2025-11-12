package com.example.demo.repository;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    // 특정 게시글 + 사용자로 이미 투표했는지 확인
    Optional<Vote> findByPostAndUser(Post post, User user);

    // 찬성 or 반대 투표 개수 카운트
    long countByPostAndVoteType(Post post, Vote.VoteType voteType);
}
