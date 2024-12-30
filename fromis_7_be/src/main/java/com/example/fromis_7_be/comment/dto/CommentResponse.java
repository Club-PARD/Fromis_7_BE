package com.example.fromis_7_be.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class CommentResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentReadResponse {
        private Long commentId;
        private Long listupId;
        private Long userId;
        private String content;
    }
}
