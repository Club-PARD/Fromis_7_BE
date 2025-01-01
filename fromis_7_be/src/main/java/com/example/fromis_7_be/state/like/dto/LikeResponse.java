package com.example.fromis_7_be.state.like.dto;

import com.example.fromis_7_be.state.like.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class LikeResponse {

    @Getter
    @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class LikeRead{
        private Long likeId;

    }
}
