package com.example.fromis_7_be.like.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LikeRequest {

    @Getter
    @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class LikeCreateRequest {
        private Long likeId;
    }
}
