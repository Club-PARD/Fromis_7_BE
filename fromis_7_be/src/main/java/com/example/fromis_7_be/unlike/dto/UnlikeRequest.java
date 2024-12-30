package com.example.fromis_7_be.unlike.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UnlikeRequest {

    @Getter
    @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class UnlikeCreateRequest {
        private Long UnlikeId;
    }
}
