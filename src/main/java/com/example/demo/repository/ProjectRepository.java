package com.example.demo.repository;

import com.example.demo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Project 엔티티의 데이터베이스 CRUD 작업을 처리하는 레포지토리
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

    /**
     * 특정 상태(status)를 가진 모든 프로젝트를 조회합니다.
     * 논리적 삭제(Soft Delete)된 프로젝트를 제외하고 조회할 때 유용합니다.
     * @param status 프로젝트 상태 (예: "ACTIVE")
     * @return 해당 상태의 프로젝트 리스트
     */
    List<Project> findByStatus(String status);
}