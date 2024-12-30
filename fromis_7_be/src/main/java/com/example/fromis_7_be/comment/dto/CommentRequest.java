package com.example.fromis_7_be.comment.dto;

import com.example.fromis_7_be.comment.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
public class CommentRequest {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "댓글 생성 요청 DTO")
    public static class CommentCreateRequest {
        @Schema(description = "content")
        @Size(max = 400, message = "댓글 내용은 최대 400자까지 가능합니다.")
        private String content;
    }

}
