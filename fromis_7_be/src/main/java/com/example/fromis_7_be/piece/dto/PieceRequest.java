package com.example.fromis_7_be.piece.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class PieceRequest {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PieceCreateRequest {
        private String title;
        private List<String> memberNames;
        private String color;
        private Integer startYear;
        private Integer startMonth;
        private Integer startDay;
        private Integer endYear;
        private Integer endMonth;
        private Integer endDay;
        private Integer highlightCount;
    }
}
