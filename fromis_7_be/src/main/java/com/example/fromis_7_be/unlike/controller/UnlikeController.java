package com.example.fromis_7_be.unlike.controller;

import com.example.fromis_7_be.unlike.service.UnlikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unlikes")
public class UnlikeController {
    private final UnlikeService unlikeService;

    @PostMapping("/{listupId}/{userId}")
    public ResponseEntity<Boolean> createUnlike (@PathVariable Long userId, @PathVariable Long listupId){
        boolean ret = unlikeService.createUnlike(userId, listupId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ret);
    }
}
