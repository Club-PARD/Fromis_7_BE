package com.example.fromis_7_be.state.like.dto;

import com.example.fromis_7_be.state.like.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LikeRequest {

    @Getter
    @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class LikeCreateRequest {
        private String color;
    }
}
