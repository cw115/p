package com.example.demo.controller;

import com.example.demo.dto.UserLoginRequest;
import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 회원 관련 API 요청을 처리하는 컨트롤러
 * 엔드포인트: /api/users
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * [POST] /api/users/register
     * 회원가입 요청 처리 (DTO 사용)
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest request) {
        try {
            User newUser = userService.registerUser(request);
            // 성공 시 201 Created 응답
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser.getNickname() + "님의 회원가입이 완료되었습니다.");
        } catch (IllegalStateException e) {
            // 이메일 중복 등 비즈니스 로직 예외 시 409 Conflict 응답
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            // 그 외 서버 오류 시 500 Internal Server Error 응답
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 중 알 수 없는 오류가 발생했습니다.");
        }
    }

    /**
     * [POST] /api/users/login
     * 로그인 요청 처리 (DTO 사용)
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        return userService.loginUser(request)
                .map(user -> {
                    // 로그인 성공 시 200 OK 응답
                	return ResponseEntity.ok(user.getUserId() + "|" + user.getNickname() + "님, 로그인에 성공했습니다.");
                })
                .orElseGet(() ->
                        // 로그인 실패 시 (이메일 없음 또는 비밀번호 불일치) 401 Unauthorized 응답
                        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이메일 또는 비밀번호가 올바르지 않습니다.")
                );
    }
}