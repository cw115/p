package com.example.demo.entity;

import jakarta.persistence.*;

/**
 * 프로젝트 정보 저장 테이블(projects)에 매핑되는 엔티티 클래스
 * project_id (PK), project_name, status (프로젝트 상태: ACTIVE, DELETED 등)
 */
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String status; // ACTIVE, COMPLETED, DELETED 등

    // 1. 기본 생성자 (JPA 사용 시 필수)
    public Project() {
        this.status = "ACTIVE"; // 기본 상태는 'ACTIVE'로 설정
    }

    // 2. Getter 메서드
    public Long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getStatus() {
        return status;
    }

    // 3. Setter 메서드
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