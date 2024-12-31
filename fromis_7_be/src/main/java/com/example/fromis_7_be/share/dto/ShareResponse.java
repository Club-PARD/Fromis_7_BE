package com.example.fromis_7_be.share.dto;

import com.example.fromis_7_be.share.entity.Share;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ShareResponse {
    @Getter
    @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class ShareRead {
        private Long shareId;
        private String shareURL;

        public static ShareRead from(Share share) {
            return new ShareRead(
                    share.getId(),
                    share.getShareURL()
            );
        }

    }
}
