package com.example.fromis_7_be.like.controller;

import com.example.fromis_7_be.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/{listupId}/{userId}")
    public ResponseEntity<String> createLike (@PathVariable Long userId, @PathVariable Long listupId){
        likeService.createLike(userId, listupId);
        return ResponseEntity.status(HttpStatus.CREATED).body("저장되었습니다.");
    }
}
