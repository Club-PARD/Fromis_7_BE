package com.example.fromis_7_be.category.controller;

import com.example.fromis_7_be.category.dto.CategoryRequest;
import com.example.fromis_7_be.category.dto.CategoryResponse;
import com.example.fromis_7_be.category.service.CategoryService;
import com.example.fromis_7_be.piece.dto.PieceRequest;
import com.example.fromis_7_be.piece.service.PieceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all/{pieceId}")
    @Operation(summary = "category 전체 조회")
    public List<CategoryResponse.CategoryReadResponse> readCategoryByPiece(@PathVariable Long pieceId){
        return categoryService.readCategoryByPiece(pieceId);
    }

    @DeleteMapping("{categoryId}")
    @Operation(summary = "category 삭제, categoryId 참조")
    public void deleteCategoryByCategoryId(@PathVariable Long categoryId){
        categoryService.delete(categoryId);
    }

}
