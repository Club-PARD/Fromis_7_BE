package com.example.fromis_7_be.user.dto;

import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.userpiece.dto.UserPieceResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class UserResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReadUser{
        private Long userId;
        private String name;
        private String image;
        private List<UserPieceResponse.UserPieceReadResponse> userPieces;

        public static ReadUser from(User user,  List<UserPieceResponse.UserPieceReadResponse> userPieces){
            return new ReadUser(user.getId(), user.getName(), user.getProfileImg(), userPieces);
        }
    }

    @Getter
    @Builder
    public static class ImageRet{
        private String imageUrl;
    }
}
