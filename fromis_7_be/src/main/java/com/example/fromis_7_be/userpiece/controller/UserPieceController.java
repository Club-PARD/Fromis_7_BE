package com.example.fromis_7_be.userpiece.controller;

import com.example.fromis_7_be.userpiece.dto.UserPieceRequest;
import com.example.fromis_7_be.userpiece.dto.UserPieceResponse;
import com.example.fromis_7_be.userpiece.service.UserPieceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-pieces")
public class UserPieceController {
    private final UserPieceService userPieceService;

    @PostMapping("/{userId}/{pieceId}")
    @Operation(summary = "user와 pieceId의 연관관계 생성")
    public ResponseEntity<UserPieceResponse.UserPieceReadResponse> createUserPiece(
            @PathVariable Long userId,
            @PathVariable Long pieceId,
            @RequestBody UserPieceRequest.CreateUserPieceRequest request) {

        UserPieceResponse.UserPieceReadResponse response = userPieceService.createUserPiece(userId, pieceId, request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userPieceId}")
    @Operation(summary = "userpiece 정보 가져오기, user-pieceid 참조")
    public ResponseEntity<UserPieceResponse.UserPieceReadResponse> getUserPiece(@PathVariable Long userPieceId) {
        UserPieceResponse.UserPieceReadResponse response = userPieceService.getUserPiece(userPieceId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userPieceId}")
    @Operation(summary = "userpiece 정보 삭제, user-pieceid 참조")
    public ResponseEntity<Void> deleteUserPiece(@PathVariable Long userPieceId) {
        userPieceService.deleteUserPiece(userPieceId);
        return ResponseEntity.noContent().build();
    }
}
