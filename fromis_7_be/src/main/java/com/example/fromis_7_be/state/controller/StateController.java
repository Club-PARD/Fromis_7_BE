package com.example.fromis_7_be.state.controller;

import com.example.fromis_7_be.state.align.service.AlignService;
import com.example.fromis_7_be.state.like.service.LikeService;
import com.example.fromis_7_be.state.unlike.service.UnlikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/states")
public class StateController {
    private final LikeService likeService;
    private final AlignService alignService;
    private final UnlikeService unlikeService;

    @PostMapping("/likes/{listId}/{userId}")
    public ResponseEntity<Boolean> createLike (@PathVariable Long userId, @PathVariable Long listId){
        boolean ret = likeService.createLike(userId, listId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ret);
    }

    @PostMapping("/align/{listId}/{userId}")
    public ResponseEntity<Boolean> createAlign (@PathVariable Long userId, @PathVariable Long listId){
        boolean ret = alignService.createAlign(userId, listId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ret);
    }

    @PostMapping("/unlikes/{listId}/{userId}")
    public ResponseEntity<Boolean> createUnlike (@PathVariable Long userId, @PathVariable Long listId){
        boolean ret = unlikeService.createUnlike(userId, listId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ret);
    }
}
