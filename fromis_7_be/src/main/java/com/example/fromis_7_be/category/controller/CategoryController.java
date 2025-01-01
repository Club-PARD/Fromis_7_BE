package com.example.fromis_7_be.category.controller;

import com.example.fromis_7_be.category.dto.CategoryRequest;
import com.example.fromis_7_be.category.dto.CategoryResponse;
import com.example.fromis_7_be.category.service.CategoryService;
import com.example.fromis_7_be.piece.dto.PieceRequest;
import com.example.fromis_7_be.piece.service.PieceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("{pieceId}")
    @Operation(summary = "category 생성, pieceId 참조")
    public ResponseEntity<CategoryResponse.CategoryReadResponse> createCategoryByPiece(@PathVariable Long pieceId,
                                                                                       @RequestBody CategoryRequest.CategoryCreateRequest req) {
        CategoryResponse.CategoryReadResponse createCategory = categoryService.createCategoryByPieceId(pieceId, req);
        return ResponseEntity.ok(createCategory);
    }

    @GetMapping("/all/{pieceId}")
    @Operation(summary = "category 전체 조회")
    public List<CategoryResponse.CategoryReadResponse> readCategoryByPiece(@PathVariable Long pieceId) {
        return categoryService.readCategoryByPiece(pieceId);
    }

    @DeleteMapping("{categoryId}")
    @Operation(summary = "category 삭제, categoryId 참조")
    public void deleteCategoryByCategoryId(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
    }

    @PatchMapping("/{pieceId}/{categoryId}")
    @Operation(summary = "category 북마크(highlight) 수정하기, categoryId 참조")
    public ResponseEntity<CategoryResponse.CategoryReadResponse> updateCategoryHighlight(
            @PathVariable Long categoryId,
            @RequestParam boolean isHighlighted) {
        CategoryResponse.CategoryReadResponse updatedCategory = categoryService.updateByCategoryId(categoryId, isHighlighted);
        return ResponseEntity.ok(updatedCategory);
    }

    @PatchMapping("{categoryId}")
    @Operation(summary = "category 추가(수정) 삭제, categoryId 참조")
    public ResponseEntity<CategoryResponse.CategoryReadResponse> updateCategoryListup(
            @PathVariable Long categoryId, @RequestBody CategoryRequest.CategoryCreateRequest req) {
        CategoryResponse.CategoryReadResponse updatedCategory = categoryService.updateByCategoryListup(categoryId, req);
        return ResponseEntity.ok(updatedCategory);
    }
}