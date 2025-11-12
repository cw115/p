package com.example.demo.dto;

/**
 * 프로젝트 등록 요청 데이터를 담는 DTO
 */
public class ProjectRegisterRequest {
    private String projectName;

    // 기본 생성자 (필수)
    public ProjectRegisterRequest() {
    }

    // Getter
    public String getProjectName() {
        return projectName;
    }

    // Setter
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}