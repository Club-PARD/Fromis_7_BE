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

    @PostMapping("/{listId}/{userId}")
    public ResponseEntity<Boolean> createLike (@PathVariable Long userId, @PathVariable Long listId){
        boolean ret = likeService.createLike(userId, listId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ret);
    }
}
