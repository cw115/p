package com.example.demo.dto;

/**
 * 프로젝트 정보(이름, 상태) 수정 요청 데이터를 담는 DTO
 */
public class ProjectUpdateRequest {
    private String projectName;
    private String status; // ACTIVE, COMPLETED, DELETED 등

    // 기본 생성자 (필수)
    public ProjectUpdateRequest() {
    }

    // Getter
    public String getProjectName() {
        return projectName;
    }

    public String getStatus() {
        return status;
    }

    // Setter
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}