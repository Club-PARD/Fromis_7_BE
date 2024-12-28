package com.example.fromis_7_be.userpiece.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserPieceResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserPieceReadResponse {
        private Long userId;
        private Long pieceId;

        public static UserPieceReadResponse from(Long userId, Long pieceId) {
            return new UserPieceReadResponse(userId, pieceId);
        }
    }

}
