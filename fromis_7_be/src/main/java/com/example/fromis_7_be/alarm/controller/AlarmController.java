package com.example.fromis_7_be.alarm.controller;

import com.example.fromis_7_be.alarm.dto.MsgResponseDto;
import com.example.fromis_7_be.alarm.service.AlarmService;
import com.example.fromis_7_be.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
public class AlarmController {
    private final AlarmService alarmService;

    /**
     * SSE 구독 엔드포인트
     */
    @GetMapping("/subscribe")
    public SseEmitter subscribe(User user) {
        Long userId = user.getId(); // 인증된 사용자 ID 가져오기
        return alarmService.subscribe(userId);
    }
    @DeleteMapping("/delete/{id}")
    public MsgResponseDto deleteAlarm(@PathVariable Long id) throws IOException {
        return alarmService.deleteNotification(id);
    }
}
