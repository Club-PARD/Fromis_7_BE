package com.example.fromis_7_be.alarm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class MsgResponseDto {
    private String data;           // 추가 데이터
    private LocalDateTime createdAt;
}