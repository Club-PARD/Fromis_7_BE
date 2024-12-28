package com.example.fromis_7_be.piece.dto;

import com.example.fromis_7_be.category.entity.Category;
import com.example.fromis_7_be.piece.entity.Piece;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class PieceResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PieceReadResponse{
        private Long pieceId;
        private String title;
        private List<String> memberNames;
        private String color;
        private Integer startYear;
        private Integer startMonth;
        private Integer startDay;
        private Integer endYear;
        private Integer endMonth;
        private Integer endDay;
        private List<Category> categories;
        //private int likeCount;
        //private boolean likedByCurrentUser;

        public static PieceReadResponse from(Piece piece) {
            return PieceReadResponse.builder()
                    .pieceId(piece.getId())
                    .title(piece.getTitle())
                    .memberNames(piece.getMemberNames())
                    .color(piece.getColor())
                    .startYear(piece.getStartYear())
                    .startMonth(piece.getStartMonth())
                    .startDay(piece.getStartDay())
                    .endYear(piece.getEndYear())
                    .endMonth(piece.getEndMonth())
                    .endDay(piece.getEndDay())
                    .build();
        }
        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }
    }


}
