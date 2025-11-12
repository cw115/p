package com.example.demo.dto;

import com.example.demo.entity.Project;

/**
 * 프로젝트 조회 응답 데이터를 담는 DTO
 */
public class ProjectResponse {
    private Long projectId;
    private String projectName;
    private String status;

    // 기본 생성자
    public ProjectResponse() {
    }

    // Entity로부터 DTO 생성
    public static ProjectResponse fromEntity(Project project) {
        ProjectResponse dto = new ProjectResponse();
        dto.setProjectId(project.getProjectId());
        dto.setProjectName(project.getProjectName());
        dto.setStatus(project.getStatus());
        return dto;
    }

    // Getter
    public Long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getStatus() {
        return status;
    }

    // Setter
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}