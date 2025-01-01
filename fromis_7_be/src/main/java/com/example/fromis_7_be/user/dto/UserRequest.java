package com.example.fromis_7_be.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class UserRequest {
    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserCreateRequest{
        private String name;
        private String email;
        private String password;
    }
    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserUpdateRequest{
        private String name;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProfileUpdateRequest {
        private String name; // 추가로 소개나 연락처 같은 필드를 더 넣을 수 있음
    }

    @Getter
    @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class LoginUserRequest {
        private String email;
        private String password;
    }

    @Getter
    @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class LoginCheckRequest {
        private String email;
    }
}
