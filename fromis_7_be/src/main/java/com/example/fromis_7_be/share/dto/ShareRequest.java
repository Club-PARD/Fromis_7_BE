package com.example.fromis_7_be.share.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ShareRequest {
    @Getter
    @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class createRequest {
        private String shareURL;
    }
}
