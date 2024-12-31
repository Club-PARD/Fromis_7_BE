package com.example.fromis_7_be.user.dto;

import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.userpiece.dto.UserPieceResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

public class UserResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReadUser{
        private Long userId;
        private String name;
        private String email;
        private String image;
        private List<UserPieceResponse.UserPieceReadResponse> userPieces;


        public static ReadUser from(User user,  List<UserPieceResponse.UserPieceReadResponse> userPieces){
            return new ReadUser(user.getId(), user.getName(), user.getEmail(), user.getProfileImg(), userPieces);
        }

        public static ReadUser from(User u) {
            return new ReadUser(u.getId(), u.getName(), u.getEmail(), u.getProfileImg(), null);
        }
    }

    @Getter
    @Builder
    public static class ImageRet{
        private String imageUrl;
    }


}
