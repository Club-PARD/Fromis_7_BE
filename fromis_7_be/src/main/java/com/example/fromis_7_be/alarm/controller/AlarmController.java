package com.example.fromis_7_be.alarm.controller;

import com.example.fromis_7_be.alarm.dto.MsgResponseDto;
import com.example.fromis_7_be.alarm.service.AlarmService;
import com.example.fromis_7_be.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
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
    public SseEmitter subscribe(@RequestHeader(value = "Authorization") String authorizationHeader) {
        // Authorization 헤더에서 사용자 ID 추출
        Long userId = extractUserIdFromHeader(authorizationHeader);
        if (userId == null) {
            throw new IllegalArgumentException("유효하지 않은 Authorization 헤더입니다.");
        }
        // 사용자 ID로 AlarmService의 subscribe 메서드 호출
        return alarmService.subscribe(userId);
    }

    /**
     * 사용자 알림 메시지 리스트 반환
     */
    @GetMapping("/notifications")
    public List<String> getNotifications(@RequestHeader(value = "Authorization") String authorizationHeader) {
        Long userId = extractUserIdFromHeader(authorizationHeader);
        if (userId == null) {
            throw new IllegalArgumentException("유효하지 않은 Authorization 헤더입니다.");
        }
        return alarmService.getNotifications(userId);
    }

    @DeleteMapping("/delete/{id}")
    public MsgResponseDto deleteAlarm(@PathVariable Long id) throws IOException {
        return alarmService.deleteNotification(id);
    }

    /**
     * Authorization 헤더에서 사용자 ID 추출
     */
    private Long extractUserIdFromHeader(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            try {
                return Long.parseLong(header.substring(7)); // "Bearer " 이후의 값
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Authorization 헤더에서 유효한 사용자 ID를 추출할 수 없습니다.");
            }
        }
        throw new IllegalArgumentException("Authorization 헤더가 올바르지 않습니다.");
    }
}