package com.example.fromis_7_be.listup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ListupRequest {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListupCreateRequest {
        private String url;
        private String description;
    }

    @Getter
    @AllArgsConstructor
    public static class ListupUpdateRequest {
        private String description;
        private LocalDateTime modifiedAt;
    }
}