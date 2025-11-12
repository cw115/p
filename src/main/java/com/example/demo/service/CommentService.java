package com.example.demo.service;

import com.example.demo.dto.CommentRequest;
import com.example.demo.dto.CommentResponse;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Comment addComment(Long postId, CommentRequest dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));

        User user = userRepository.findByNickname(dto.getNickname())
                .orElseThrow(() -> new IllegalArgumentException("닉네임에 해당하는 유저가 없습니다."));

        Comment comment = new Comment(post, user, dto.getContent());
        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByPostId(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));

        return commentRepository.findByPostOrderByCreatedAtAsc(post)
                .stream()
                .map(c -> new CommentResponse(
                        c.getId(),
                        c.getContent(),
                        c.getUser().getNickname(),
                        c.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}
