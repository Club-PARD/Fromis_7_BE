package com.example.fromis_7_be.like.dto;

import com.example.fromis_7_be.like.entity.Like;
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

        public static LikeRead from(Like like) {
            return new LikeRead(
                    like.getId()
            );
        }

    }

}
