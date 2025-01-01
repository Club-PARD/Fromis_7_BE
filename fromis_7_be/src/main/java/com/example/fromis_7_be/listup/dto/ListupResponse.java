package com.example.fromis_7_be.listup.dto;

import com.example.fromis_7_be.comment.dto.CommentResponse;
import com.example.fromis_7_be.comment.entity.Comment;
import com.example.fromis_7_be.listup.entity.Listup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        private int alginCount;
        private ArrayList<CommentResponse.CommentReadResponse> comments;
        private int preferCount;

        public static ListupReadResponse from(Listup listup) {
            return ListupReadResponse.builder()
                    .listupId(listup.getListId())
                    .url(listup.getUrl())
                    .name(listup.getName())
                    .image(listup.getImage())
                    .description(listup.getDescription())
                    .likeCount(listup.getLikes() == null ? 0 : listup.getLikes().size()) // 좋아요 수 계산
                    .unlikeCount(listup.getUnlikes() == null ? 0 : listup.getUnlikes().size()) // 싫어요 수 계산
                    .alginCount(listup.getAligns() == null ? 0 : listup.getAligns().size()) // 얼라인 수 계산
                    .comments(listup.getComments() == null ? new ArrayList<>() :
                            listup.getComments().stream()
                                    .map(CommentResponse.CommentReadResponse::from)
                                    .collect(Collectors.toCollection(ArrayList::new))
                    )
                    .preferCount(listup.getLikes().size()-listup.getUnlikes().size())
                    .build();
        }

    }

}
