package com.example.fromis_7_be.category.dto;

import com.example.fromis_7_be.category.entity.Category;
import com.example.fromis_7_be.listup.dto.ListupResponse;
import com.example.fromis_7_be.listup.entity.Listup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class CategoryResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryReadResponse {
        private Long cateId;
        private String name;
        private String color;
        private Boolean isHighlighted;
        private List<ListupResponse.ListupReadResponse> lists;

        public static  CategoryReadResponse from(Category category){
            return CategoryReadResponse.builder()
                    .cateId(category.getId())
                    .name(category.getName())
                    .isHighlighted(category.getIsHighlighted())
                    .lists(category.getLists().stream()
                            .map(ListupResponse.ListupReadResponse::from) // 변환
                            .toList())
                    // 근데 카테고리에서 굳이 리스트를 가져올 필요가 있는 건가 싶은 생각.
                    // 만약 카테고리에서 리스트를 가져오지 않아도 된다면 그냥 id값만 리스트화 시키는 건 어떨지 생각해보기
                    .build();
        }
    }
}