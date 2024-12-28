package com.example.fromis_7_be.piece.controller;

import com.example.fromis_7_be.piece.dto.PieceRequest;
import com.example.fromis_7_be.piece.dto.PieceResponse;
import com.example.fromis_7_be.piece.service.PieceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/piece")
public class PieceController {
    private final PieceService pieceService;

    @PostMapping("/{userId}")
    public void createPieceByUser(@PathVariable Long userId,
                                  @RequestBody PieceRequest.PieceCreateRequest req){
        pieceService.createPieceByUserId(userId, req);
    }
    @GetMapping("/user/{userId}")
    public List<PieceResponse.PieceReadResponse> readPieceByUser(@PathVariable Long userId){
        return pieceService.readPieceByUser(userId);
    }

    @PatchMapping("/{pieceId}")
    public PieceResponse.PieceReadResponse updatePiece(@PathVariable Long pieceId, @RequestBody PieceRequest.PieceCreateRequest req){
        return pieceService.update(pieceId, req);
    }
    @DeleteMapping("/{pieceId}")
    public void deletePiece(@PathVariable Long pieceId){
        pieceService.delete(pieceId);
    }
}
