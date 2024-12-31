package com.example.fromis_7_be.alarm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MsgResponseDto {
    private String message; // 응답 메시지
    private int statusCode; // HTTP 상태 코드
}