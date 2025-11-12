package com.example.demo.dto;

/**
 * 로그인 요청 데이터를 담는 DTO
 */
public class UserLoginRequest {
    private String email;
    private String password;

    // 기본 생성자 (필수)
    public UserLoginRequest() {
    }

    // Getter
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setter
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}