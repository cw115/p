package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    

    /**
     * 닉네임을 통해 User 엔티티를 조회합니다. (시간 슬롯 등록에 사용)
     * @param nickname 사용자 닉네임
     * @return User 엔티티 (Optional)
     */
    Optional<User> findByNickname(String nickname);
}