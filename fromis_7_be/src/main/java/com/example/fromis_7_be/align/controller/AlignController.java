package com.example.fromis_7_be.align.controller;

import com.example.fromis_7_be.align.service.AlignService;
import com.example.fromis_7_be.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/align")
public class AlignController {
    private final AlignService alignService;

    @PostMapping("/{listupId}/{userId}")
    public ResponseEntity<Boolean> createAlign (@PathVariable Long userId, @PathVariable Long listupId){
        boolean ret = alignService.createAlign(userId, listupId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ret);
    }
}
