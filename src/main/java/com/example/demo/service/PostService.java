package com.example.demo.service;

import com.example.demo.dto.PostRequest;
import com.example.demo.dto.PostResponse;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    public PostService(PostRepository postRepository, ProjectRepository projectRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.postRepository = postRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    @Transactional
    public Post createPost(PostRequest dto) {
        User user = userRepository.findByNickname(dto.getNickname())
                .orElseThrow(() -> new IllegalArgumentException("닉네임에 해당하는 유저를 찾을 수 없습니다."));

        Project project = null;
        if (dto.getProjectId() != null) {
            project = projectRepository.findById(dto.getProjectId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 Project ID를 찾을 수 없습니다."));
        }

        Post post = new Post(project, user, dto.getTitle(), dto.getContent(), dto.getType());
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> findAll() { 
        return postRepository.findAll().stream()
                .map(post -> {
                    long votesFor = voteRepository.countByPostAndVoteType(post, Vote.VoteType.찬성);
                    long votesAgainst = voteRepository.countByPostAndVoteType(post, Vote.VoteType.반대);
                    
                    return new PostResponse(
                        post.getId(), 
                        post.getTitle(), 
                        post.getContent(), 
                        post.getUser().getNickname(), 
                        post.getType().name().toLowerCase(), 
                        post.getCreatedAt(),
                        votesFor,
                        votesAgainst
                    );
                })
                .collect(Collectors.toList());
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }
    
    // 🚨 게시글 수정 로직 추가
    @Transactional
    public Post updatePost(Long postId, PostRequest dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        
        // Post 엔티티에 추가한 Setter를 사용해 필드 업데이트
        post.setTitle(dto.getTitle()); 
        post.setContent(dto.getContent()); 
        
        // @Transactional 덕분에 postRepository.save(post) 호출 없이도 DB에 반영됨
        return post; 
    }

    // 🚨 게시글 삭제 로직 추가
    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
                
        // Post에 연결된 Comment와 Vote는 Cascade 설정에 의해 함께 삭제됨
        postRepository.delete(post);
    }
}