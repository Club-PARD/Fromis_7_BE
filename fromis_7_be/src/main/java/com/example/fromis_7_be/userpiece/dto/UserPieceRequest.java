package com.example.fromis_7_be.userpiece.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserPieceRequest {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateUserPieceRequest {
        private Long userId;   // 유저 ID
        private Long pieceId;  // 피스 ID
    }
}
