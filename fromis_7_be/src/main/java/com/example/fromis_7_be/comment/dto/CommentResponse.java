package com.example.fromis_7_be.comment.dto;

import com.example.fromis_7_be.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public class CommentResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentReadResponse {
        private Long commentId;
        private Long listupId;
        private Long userId;
        private String content;

        public static CommentReadResponse from(Comment comment) {
            return CommentReadResponse.builder()
                    .commentId(comment.getId())
                    .listupId(comment.getListup().getListId())
                    .userId(comment.getUser().getId())
                    .content(comment.getContent())
                    .build();
        }
    }



}
