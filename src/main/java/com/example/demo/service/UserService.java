package com.example.demo.service;

import com.example.demo.dto.UserLoginRequest;
import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 회원가입 및 로그인 비즈니스 로직을 처리하는 서비스
 */
@Service
public class UserService {

    private final UserRepository userRepository;


    // 💡 Lombok의 @RequiredArgsConstructor 대신 수동으로 생성자를 작성하여 의존성 주입(DI)을 처리합니다.
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원가입을 처리합니다.
     * @param request 회원가입 요청 DTO
     * @return 저장된 User 엔티티
     * @throws IllegalStateException 이메일이 이미 존재하는 경우 발생
     */
    @Transactional
    public User registerUser(UserRegisterRequest request) {
        // 1. 이메일 중복 검사
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("이미 사용 중인 이메일입니다.");
        }

        // 2. 비밀번호 암호화 (DTO에서 받은 평문 비밀번호 사용)
        // 실제 구현: String hashedPassword = passwordEncoder.encode(request.getPassword());
        String hashedPassword = request.getPassword() + "HASH"; // 기초 구현을 위한 임시 해시

        // 3. User 엔티티 생성 및 저장
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPasswordHash(hashedPassword);
        user.setNickname(request.getNickname());

        return userRepository.save(user);
    }

    /**
     * 로그인을 처리합니다.
     * @param request 로그인 요청 DTO
     * @return 로그인 성공 시 User 엔티티, 실패 시 Optional.empty()
     */
    public Optional<User> loginUser(UserLoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // 1. 비밀번호 검증 (DTO에서 받은 평문 비밀번호와 DB의 해시 비교)
            // 실제 구현: passwordEncoder.matches(request.getPassword(), user.getPasswordHash())
            String expectedHash = request.getPassword() + "HASH";

            if (user.getPasswordHash().equals(expectedHash)) {
                // 로그인 성공
                return Optional.of(user);
            }
        }
        // 이메일이 없거나 비밀번호가 일치하지 않음
        return Optional.empty();
    }
}