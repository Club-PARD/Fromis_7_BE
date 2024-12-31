package com.example.fromis_7_be.piece.controller;

import com.example.fromis_7_be.piece.dto.PieceRequest;
import com.example.fromis_7_be.piece.dto.PieceResponse;
import com.example.fromis_7_be.piece.service.PieceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pieces")
public class PieceController {
    private final PieceService pieceService;

    @PostMapping("/{userId}")
    @Operation(summary = "piece 생성, userid 참조")
    public ResponseEntity<PieceResponse.PieceReadResponse> createPieceByUser(@PathVariable Long userId,
                                                                             @RequestBody PieceRequest.PieceCreateRequest req){
        PieceResponse.PieceReadResponse createPieceResponse =  pieceService.createPieceByUserId(userId, req);
        return ResponseEntity.ok().body(createPieceResponse);
    }
    @GetMapping("/all/{userId}")
    @Operation(summary = "piece 모두 불러오기, userid 참조")
    public List<PieceResponse.PieceReadResponse> readPieceByUser(@PathVariable Long userId){
        return pieceService.readPieceByUser(userId);
    }

    @PatchMapping("/{pieceId}")
    @Operation(summary = "piece 하나의 정보 수정하기, pieceid 참조")
    public PieceResponse.PieceReadResponse updatePiece(@PathVariable Long pieceId, @RequestBody PieceRequest.PieceCreateRequest req){
        return pieceService.update(pieceId, req);
    }

    @DeleteMapping("/{pieceId}")
    @Operation(summary = "piece 하나의 정보 삭제하기, pieceid 참조")
    public void deletePiece(@PathVariable Long pieceId){
        pieceService.delete(pieceId);
    }
}