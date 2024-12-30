package com.example.fromis_7_be.align.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AlignRequest {

    @Getter
    @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class AlignCreateRequest {
        private Long alignId;
    }
}
