package com.example.demo.service;

import com.example.demo.dto.ProjectRegisterRequest;
import com.example.demo.dto.ProjectResponse;
import com.example.demo.dto.ProjectUpdateRequest;
import com.example.demo.entity.Project;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Arrays;

/**
 * 프로젝트 생성, 삭제 및 관리 비즈니스 로직을 처리하는 서비스
 */
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    // 유효한 상태 값 정의
    private final List<String> VALID_STATUSES = Arrays.asList("ACTIVE", "COMPLETED", "DELETED");

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * 새로운 프로젝트를 등록합니다.
     */
    @Transactional
    public ProjectResponse registerProject(ProjectRegisterRequest request) {
        Project project = new Project();
        project.setProjectName(request.getProjectName());
        // status는 Project 엔티티 기본 생성자에서 "ACTIVE"로 설정됨

        Project savedProject = projectRepository.save(project);
        return ProjectResponse.fromEntity(savedProject);
    }

    /**
     * 프로젝트 정보(이름, 상태)를 업데이트합니다.
     */
    @Transactional
    public ProjectResponse updateProject(Long projectId, ProjectUpdateRequest request) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("업데이트할 프로젝트 ID: " + projectId + "를 찾을 수 없습니다."));

        // 1. 프로젝트 이름 업데이트 (요청에 포함된 경우에만)
        if (request.getProjectName() != null && !request.getProjectName().trim().isEmpty()) {
            project.setProjectName(request.getProjectName());
        }

        // 2. 프로젝트 상태 업데이트 (요청에 포함된 경우에만)
        if (request.getStatus() != null) {
            String newStatus = request.getStatus().toUpperCase();
            // 상태 유효성 검사
            if (!VALID_STATUSES.contains(newStatus)) {
                throw new IllegalArgumentException("유효하지 않은 프로젝트 상태입니다: " + newStatus + ". 유효한 상태: " + VALID_STATUSES);
            }
            project.setStatus(newStatus);
        }

        Project updatedProject = projectRepository.save(project);
        return ProjectResponse.fromEntity(updatedProject);
    }

    /**
     * 현재 진행 중인 (ACTIVE 상태) 모든 프로젝트 리스트를 조회합니다.
     */
    public List<ProjectResponse> getAllActiveProjects() {
        // "ACTIVE" 상태의 프로젝트만 조회
        List<Project> activeProjects = projectRepository.findByStatus("ACTIVE");

        // Entity 리스트를 DTO 리스트로 변환하여 반환
        return activeProjects.stream()
                .map(ProjectResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 프로젝트 ID로 프로젝트 정보를 조회합니다. (DELETED 상태는 제외)
     */
    public ProjectResponse getActiveProject(Long projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);

        Project project = projectOptional.orElseThrow(() ->
                new ResourceNotFoundException("프로젝트 ID: " + projectId + "를 찾을 수 없습니다.")
        );

        // 삭제된 프로젝트는 조회 불가
        if ("DELETED".equals(project.getStatus())) {
            throw new ResourceNotFoundException("프로젝트 ID: " + projectId + "는 삭제되었습니다.");
        }

        return ProjectResponse.fromEntity(project);
    }

    /**
     * 프로젝트를 논리적 삭제(status='DELETED')합니다.
     */
    @Transactional
    public void softDeleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("삭제할 프로젝트 ID: " + projectId + "를 찾을 수 없습니다."));

        if ("DELETED".equals(project.getStatus())) {
            throw new IllegalStateException("이미 삭제된 프로젝트입니다.");
        }

        project.setStatus("DELETED"); // 상태를 'DELETED'로 변경
        projectRepository.save(project);
    }
}