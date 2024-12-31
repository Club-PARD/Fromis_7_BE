package com.example.fromis_7_be.alarm.dto;

import com.example.fromis_7_be.alarm.entity.Alarm;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AlarmResponse {
    private Long id;
    private String message;
    private String type;
    private LocalDateTime createdAt;
    private boolean isRead;

    public static AlarmResponse from(Alarm alarm) {
        return AlarmResponse.builder()
                .id(alarm.getId())
                .message(alarm.getMessage())
                .type(alarm.getType().name())
                .createdAt(alarm.getCreatedAt())
                .isRead(alarm.isRead())
                .build();
    }
}
