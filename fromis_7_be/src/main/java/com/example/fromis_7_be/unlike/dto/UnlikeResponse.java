package com.example.fromis_7_be.unlike.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class UnlikeResponse {

    @Getter
    @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class UnlikeRead{
        private Long UnlikeId;
    }

}
