package com.example.demo.controller;

import com.example.demo.dto.ProjectRegisterRequest;
import com.example.demo.dto.ProjectResponse;
import com.example.demo.dto.ProjectUpdateRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 프로젝트 관련 API 요청을 처리하는 컨트롤러
 * 엔드포인트: /api/projects
 */
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * [POST] /api/projects
     * 새로운 프로젝트를 등록합니다.
     */
    @PostMapping
    public ResponseEntity<ProjectResponse> registerProject(@RequestBody ProjectRegisterRequest request) {
        try {
            ProjectResponse response = projectService.registerProject(request);
            // 성공 시 201 Created 응답
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            System.err.println("프로젝트 등록 오류: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * [PUT] /api/projects/{projectId}
     * 프로젝트 정보(이름 및 상태)를 수정합니다.
     */
    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long projectId, @RequestBody ProjectUpdateRequest request) {
        try {
            ProjectResponse response = projectService.updateProject(projectId, request);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            // 프로젝트를 찾을 수 없을 때 404 Not Found 응답
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            // 유효하지 않은 상태 값일 때 400 Bad Request 응답
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * [GET] /api/projects
     * 현재 진행 중인 (ACTIVE 상태) 모든 프로젝트 리스트를 조회합니다.
     */
    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllActiveProjects() {
        List<ProjectResponse> response = projectService.getAllActiveProjects();
        return ResponseEntity.ok(response);
    }

    /**
     * [GET] /api/projects/{projectId}
     * 프로젝트 ID로 프로젝트 정보를 조회합니다. (DELETED 상태 제외)
     */
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long projectId) {
        try {
            ProjectResponse response = projectService.getActiveProject(projectId);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            // 프로젝트를 찾을 수 없거나 DELETED 상태일 때 404 Not Found 응답
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * [DELETE] /api/projects/{projectId}
     * 프로젝트를 논리적 삭제(status=DELETED)합니다.
     */
    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable Long projectId) {
        try {
            projectService.softDeleteProject(projectId);
            // 성공 시 204 No Content 응답 (리소스 삭제 성공)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("프로젝트 ID " + projectId + "가 성공적으로 삭제 처리되었습니다.");
        } catch (ResourceNotFoundException e) {
            // 삭제할 프로젝트를 찾을 수 없을 때 404 Not Found 응답
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            // 이미 삭제된 프로젝트일 때 409 Conflict 응답
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}