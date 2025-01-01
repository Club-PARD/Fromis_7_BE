package com.example.fromis_7_be.state.controller;

import com.example.fromis_7_be.state.align.service.AlignService;
import com.example.fromis_7_be.state.like.dto.LikeRequest;
import com.example.fromis_7_be.state.like.dto.LikeResponse;
import com.example.fromis_7_be.state.like.service.LikeService;
import com.example.fromis_7_be.state.unlike.service.UnlikeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/states")
public class StateController {
    private final LikeService likeService;
    private final AlignService alignService;
    private final UnlikeService unlikeService;

    @PostMapping("/likes/{listId}/{userId}")
    @Operation(summary = "Like 생성하기. userId, listId 참조")
    public ResponseEntity<Boolean> createLike (@PathVariable Long userId, @PathVariable Long listId) {
        boolean ret = likeService.createLike(userId, listId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ret);
    }

    @PostMapping("/align/{listId}/{userId}")
    @Operation(summary = "align 생성하기. userId, listId 참조")
    public ResponseEntity<Boolean> createAlign (@PathVariable Long userId, @PathVariable Long listId){
        boolean ret = alignService.createAlign(userId, listId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ret);
    }

    @PostMapping("/unlikes/{listId}/{userId}")
    @Operation(summary = "unlike 생성하기. userId, listId 참조")
    public ResponseEntity<Boolean> createUnlike (@PathVariable Long userId, @PathVariable Long listId){
        boolean ret = unlikeService.createUnlike(userId, listId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ret);
    }
}
