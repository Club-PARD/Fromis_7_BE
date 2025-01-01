package com.example.fromis_7_be.alarm.service;

import com.example.fromis_7_be.alarm.dto.AlarmResponse;
import com.example.fromis_7_be.alarm.dto.MsgResponseDto;
import com.example.fromis_7_be.alarm.entity.Alarm;
import com.example.fromis_7_be.alarm.repository.AlarmRepository;
import com.example.fromis_7_be.category.entity.Category;
import com.example.fromis_7_be.piece.entity.Piece;
import com.example.fromis_7_be.alarm.entity.Alarm.AlarmType;
import com.example.fromis_7_be.piece.repository.PieceRepository;
import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;

    // SseEmitter 관리 맵
    private final Map<Long, SseEmitter> sseEmitters = new ConcurrentHashMap<>();
    // 알림 카운트 관리 맵

    /**
     * SSE 구독
     */
    public SseEmitter subscribe(Long userId) {
        // 1. SseEmitter 생성
        SseEmitter sseEmitter = new SseEmitter(30 * 60 * 1000L); // 30분 타임아웃


        // 2. 초기 연결
        try {
            sseEmitter.send(SseEmitter.event().name("connect").data("Connected to SSE"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. SseEmitter 저장
        sseEmitters.put(userId, sseEmitter);

        // 4. 연결 종료 처리
        sseEmitter.onCompletion(() -> sseEmitters.remove(userId));
        sseEmitter.onTimeout(() -> sseEmitters.remove(userId));
        sseEmitter.onError(e -> sseEmitters.remove(userId));

        return sseEmitter;
    }

    /**
     * 알림 전송 (공통 로직)
     */
    private void sendNotification(Long userId, String eventName, String message) {
        SseEmitter sseEmitter = sseEmitters.get(userId);
        if (sseEmitter != null) {
            try {
                sseEmitter.send(SseEmitter.event().name(eventName).data(message));
            } catch (IOException e) {
                sseEmitters.remove(userId);
                System.err.println("Failed to send notification to userId: " + userId + ", Error: " + e.getMessage());
            }
        }
    }


    /**
     * 알림 저장 및 전송
     */
    private void saveAndNotify(User user, String message, AlarmType type, String eventName) {
        // 알림 데이터 저장
        Alarm alarm = Alarm.builder()
                .user(user)
                .message(message)
                .type(type)
                .createdAt(LocalDateTime.now())
                .build();
        alarmRepository.save(alarm);

        // SSE 알림 전송
        sendNotification(user.getId(), eventName, message);
    }

    /**
     * 사용자 알림 조회
     */
    public List<String> getNotifications(Long userId) {
        // 알림 메시지를 DB에서 조회하거나 메모리 맵에서 가져옵니다.
        return alarmRepository.findAllByUserId(userId).stream()
                .map(Alarm::getMessage) // 알림 메시지만 추출
                .collect(Collectors.toList());
    }

    /**
     * piece 생성 알림
     */
    public void notifyPieceCreated(User user, Piece piece) {
        String title = piece.getTitle() != null ? piece.getTitle() : "새로운 링크";
        String message = title + " 링크가 생성되었어요!";

        Alarm alarm = Alarm.builder()
                .user(user)
                .message(message)
                .type(Alarm.AlarmType.NEW_PIECE)
                .createdAt(LocalDateTime.now())
                .isRead(false)
                .build();
        alarmRepository.save(alarm);

        // 알림 전송
        sendNotification(user.getId(), "newPiece", message);
    }
    /**
     * 하이라이트된 카테고리 알림 전송
     */
    public void notifyCategoryCreated(User user, Category category) {
        String name = category.getName() != null ? category.getName() : "새로운 조각";
        String message = name + "조각이 하이라이트 되었어요!";

        Alarm alarm = Alarm.builder()
                .user(user)
                .message(message)
                .type(Alarm.AlarmType.NEW_PIECE)
                .createdAt(LocalDateTime.now())
                .isRead(false)
                .build();
        alarmRepository.save(alarm);

        // 알림 전송
        sendNotification(user.getId(), "highlightedCategory", message);
    }


}