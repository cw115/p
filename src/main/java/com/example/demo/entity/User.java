package com.example.demo.entity;

import jakarta.persistence.*;

/**
 * 회원정보 저장 테이블(users)에 매핑되는 엔티티 클래스
 * user_id (PK), email (Unique), password_hash, nickname
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String nickname;

    // 1. 기본 생성자 (JPA 사용 시 필수)
    public User() {
    }

    // 2. 전체 필드 생성자 (옵션)
    public User(Long userId, String email, String passwordHash, String nickname) {
        this.userId = userId;
        this.email = email;
        this.passwordHash = passwordHash;
        this.nickname = nickname;
    }

    // 3. Getter 메서드
    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getNickname() {
        return nickname;
    }

    // 4. Setter 메서드
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}