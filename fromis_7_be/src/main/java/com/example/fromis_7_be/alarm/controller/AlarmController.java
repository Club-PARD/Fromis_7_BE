package com.example.fromis_7_be.alarm.controller;

import com.example.fromis_7_be.alarm.dto.MsgResponseDto;
import com.example.fromis_7_be.alarm.service.AlarmService;
import com.example.fromis_7_be.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
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
    @Operation(summary = "알림 자동 생성, 프론트는 신경쓰지 않으셔도 됩니다." )
    public SseEmitter subscribe(@RequestParam Long userId) {
        //@RequestHeader(value = "Authorization") String authorizationHeader
        // Authorization 헤더에서 사용자 ID 추출
//        Long userId = extractUserIdFromHeader(authorizationHeader);
//        if (userId == null) {
//            throw new IllegalArgumentException("유효하지 않은 Authorization 헤더입니다.");
//        }
        // 사용자 ID로 AlarmService의 subscribe 메서드 호출
        return alarmService.subscribe(userId);
    }

    /**
     * 사용자 알림 메시지 리스트 반환
     */
    @GetMapping("/notifications")
    @Operation(summary = "알림 List<String> 형식으로 response 합니다.")
    public MsgResponseDto getNotifications(@RequestParam Long userId) {
        List<String> notifications = alarmService.getNotifications(userId);
        return new MsgResponseDto(notifications, LocalDateTime.now());
    }

    /**
     * Authorization 헤더에서 사용자 ID 추출
     */
//    private Long extractUserIdFromHeader(String header) {
//        if (header != null && header.startsWith("Bearer ")) {
//            try {
//                return Long.parseLong(header.substring(7)); // "Bearer " 이후의 값
//            } catch (NumberFormatException e) {
//                throw new IllegalArgumentException("Authorization 헤더에서 유효한 사용자 ID를 추출할 수 없습니다.");
//            }
//        }
//        throw new IllegalArgumentException("Authorization 헤더가 올바르지 않습니다.");
//    }
}