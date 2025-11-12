package com.example.demo.service;

import com.example.demo.entity.TimeSlot;
import com.example.demo.entity.User;
import com.example.demo.repository.TimeSlotRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;

@Service
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final UserRepository userRepository; // UserRepository 추가

    @PersistenceContext
    private EntityManager em;

    public TimeSlotService(TimeSlotRepository timeSlotRepository, UserRepository userRepository) {
        this.timeSlotRepository = timeSlotRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveTimeSlot(String nickname, int dayOfWeek, String startTimeStr, String endTimeStr) {
        // 1. 닉네임으로 사용자 검색 및 유효성 검사 (Foreign Key 오류 방지)
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("사용자 닉네임을 찾을 수 없습니다. (DB에 등록된 닉네임을 사용해야 합니다.)"));

        // 2. 검색된 사용자 엔티티의 ID를 사용하여 TimeSlot 저장
        // User 엔티티에 getUserId() 메소드가 있다고 가정합니다.
        User userRef = em.getReference(User.class, user.getUserId()); 

        Time startTime = Time.valueOf(startTimeStr);
        Time endTime = Time.valueOf(endTimeStr);

        TimeSlot slot = new TimeSlot(userRef, dayOfWeek, startTime, endTime);
        timeSlotRepository.save(slot);
    }

    @Transactional(readOnly = true)
    public List<TimeSlot> getUserTimeSlots(String nickname) {
        // 1. 닉네임으로 사용자 검색
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("사용자 닉네임을 찾을 수 없습니다."));
                
        // 2. 검색된 사용자의 ID를 사용하여 슬롯 조회
        return timeSlotRepository.findByUser_UserId(user.getUserId());
    }
}