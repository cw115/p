package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final VoteService voteService;

    public PostController(PostService postService, CommentService commentService, VoteService voteService) {
        this.postService = postService;
        this.commentService = commentService;
        this.voteService = voteService;
    }

    // 1. 게시글 등록: POST /api/posts
    @PostMapping
    public Post createPost(@RequestBody PostRequest request) {
        return postService.createPost(request);
    }

    // 2. 게시글 전체 조회: GET /api/posts
    @GetMapping
    public List<PostResponse> getAllPosts() {
        return postService.findAll();
    }
    
    // 3. 댓글 등록: POST /api/posts/{postId}/comments
    @PostMapping("/{postId}/comments")
    public Comment addComment(@PathVariable("postId") Long postId, @RequestBody CommentRequest dto) {
        // ✅ 수정: 닉네임 기반으로 User 조회
        return commentService.addComment(postId, dto);
    }
    
    // 4. 댓글 조회: GET /api/posts/{postId}/comments
    @GetMapping("/{postId}/comments") 
    public List<CommentResponse> getComments(@PathVariable("postId") Long postId) {
        return commentService.getCommentsByPostId(postId); 
    }

    // 5. 투표: POST /api/posts/{postId}/vote
    @PostMapping("/{postId}/vote")
    @ResponseStatus(code = HttpStatus.NO_CONTENT) // 성공 시 204 No Content 반환
    public void vote(@PathVariable("postId") Long postId, @RequestBody VoteRequest dto) {
        voteService.vote(postId, dto);
        // Entity를 반환하지 않아 ByteBuddyProxy 직렬화 오류 방지
    }

    // 6. 게시글 수정: PUT /api/posts/{postId}
    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable("postId") Long postId, @RequestBody PostRequest request) {
        return postService.updatePost(postId, request);
    }

    // 7. 게시글 삭제: DELETE /api/posts/{postId}
    @DeleteMapping("/{postId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
    }
}
