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
        private String color;
        private boolean liked;

        public static LikeResponse.LikeRead form(Long likeId, String color, boolean liked) {
            return LikeResponse.LikeRead.builder()
                    .likeId(likeId)
                    .color(color)
                    .liked(liked)
                    .build();
        }
    }

}
