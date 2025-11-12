package com.example.demo.dto;

/**
 * 회원가입 요청 데이터를 담는 DTO
 */
public class UserRegisterRequest {
    private String email;
    private String password;
    private String nickname;

    // 기본 생성자 (필수)
    public UserRegisterRequest() {
    }

    // Getter
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    // Setter
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}