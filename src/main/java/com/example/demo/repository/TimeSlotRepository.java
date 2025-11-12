package com.example.demo.repository;

import com.example.demo.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    List<TimeSlot> findByUser_UserId(Long userId);

    Optional<TimeSlot> findByUser_UserIdAndDayOfWeek(Long userId, int dayOfWeek);
}
