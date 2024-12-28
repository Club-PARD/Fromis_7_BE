package com.example.fromis_7_be.userpiece.controller;

import com.example.fromis_7_be.userpiece.dto.UserPieceRequest;
import com.example.fromis_7_be.userpiece.dto.UserPieceResponse;
import com.example.fromis_7_be.userpiece.service.UserPieceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserPieceController {
    private final UserPieceService userPieceService;

    // 유저와 피스를 연결하는 API
    @PostMapping
    public ResponseEntity<UserPieceResponse.UserPieceReadResponse> createUserPiece(
            @RequestBody UserPieceRequest.CreateUserPieceRequest request) {

        // UserPiece 생성
        UserPieceResponse.UserPieceReadResponse response = userPieceService.createUserPiece(request);

        return ResponseEntity.ok(response);
    }
}
