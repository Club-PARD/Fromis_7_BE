package com.example.fromis_7_be.category.dto;

import com.example.fromis_7_be.listup.dto.ListupRequest;
import com.example.fromis_7_be.listup.entity.Listup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


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
        private List<ListupRequest.ListupCreateRequest> listups;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HighlightUpdateRequest {
        private Boolean isHighlighted;
    }
}
