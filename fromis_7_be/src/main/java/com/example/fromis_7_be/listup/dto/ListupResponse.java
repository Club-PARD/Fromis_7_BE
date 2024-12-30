package com.example.fromis_7_be.listup.dto;

import com.example.fromis_7_be.comment.entity.Comment;
import com.example.fromis_7_be.listup.entity.Listup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ListupResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListupReadResponse{
        private Long listupId;
        private String url;
        private String name;
        private String image;
        private String description;
        private int likeCount;
        private int unlikeCount;
        private List<Comment> comments;

        public static ListupReadResponse from(Listup listup) {
            return ListupReadResponse.builder()
                    .listupId(listup.getListId())
                    .url(listup.getUrl())
                    .name(listup.getName())
                    .image(listup.getImage())
                    .description(listup.getDescription())
                    .likeCount(listup.getLikes() == null ? 0 : listup.getLikes().size()) // 좋아요 수 계산
                    .unlikeCount(listup.getUnlikes() == null ? 0 : listup.getUnlikes().size()) // 좋아요 수 계산
                    .comments(listup.getComments())
                    .build();
        }

    }

}
