package com.example.fromis_7_be.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class CategoryRequest {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryCreateRequest{
        private String color;
        private String name;
        private Boolean isHighlighted; // 하이라이트 상태
        private Long pieceId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HighlightUpdateRequest {
        private Boolean isHighlighted;
    }
}
