package com.example.fromis_7_be.alarm.repository;

import com.example.fromis_7_be.alarm.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findAllByUserId(Long userId);
}