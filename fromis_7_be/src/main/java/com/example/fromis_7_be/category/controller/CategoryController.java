package com.example.fromis_7_be.category.controller;

import com.example.fromis_7_be.category.dto.CategoryRequest;
import com.example.fromis_7_be.category.service.CategoryService;
import com.example.fromis_7_be.piece.dto.PieceRequest;
import com.example.fromis_7_be.piece.service.PieceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("{pieceId}")
    @Operation(summary = "category 생성, pieceId 참조")
    public void createCategoryByPiece(@PathVariable Long pieceId,
                                      @RequestBody CategoryRequest.CategoryCreateRequest req){
        categoryService.createCategoryByPieceId(pieceId, req);
    }
}
