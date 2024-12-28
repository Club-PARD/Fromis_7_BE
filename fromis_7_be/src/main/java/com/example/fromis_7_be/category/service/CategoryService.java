package com.example.fromis_7_be.category.service;

import com.example.fromis_7_be.category.dto.CategoryRequest;
import com.example.fromis_7_be.category.dto.CategoryResponse;
import com.example.fromis_7_be.category.entity.Category;
import com.example.fromis_7_be.category.repository.CategoryRepository;
import com.example.fromis_7_be.piece.entity.Piece;
import com.example.fromis_7_be.piece.repository.PieceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final PieceRepository pieceRepository;

    public void createCategoryByPieceId(Long pieceId, CategoryRequest.CategoryCreateRequest req){
        Piece piece = pieceRepository.findById(pieceId)
                .orElseThrow(() -> new NoSuchElementException("찾으시는 piece 정보: " + pieceId + "가 존재하지 않습니다."));
        Category category = Category.from(req.getName(), req.getColor(), false, piece);
        categoryRepository.save(category);
    }
    public List<CategoryResponse.CategoryReadResponse> readCategoryByPiece(Long pieceId){
        Piece piece = pieceRepository.findById(pieceId)
                .orElseThrow(() -> new NoSuchElementException("찾으시는 piece 정보: " + pieceId + "가 존재하지 않습니다."));

        return categoryRepository.findAllByPiece(piece).stream()
                .map(category -> {
                    return new CategoryResponse.CategoryReadResponse(
                            category.getId(),
                            category.getName(),
                            category.getColor(),
                            category.getIsHighlighted(),
                            category.getLists()
                    );
                }).collect(Collectors.toList());
    }
    public CategoryResponse.CategoryReadResponse update(Long categoryId, CategoryRequest.CategoryCreateRequest req){
        Optional<Category> b = categoryRepository.findById(categoryId);
        Category category = b.get();
        category.update(req.getName(), req.getColor(), req.getIsHighlighted());
        categoryRepository.save(category);

        return new CategoryResponse.CategoryReadResponse(
                category.getId(),
                category.getName(), // 예: 이름
                category.getColor(), // 예: 색상
                category.getIsHighlighted(), // 예: 강조 여부
                category.getLists()
        );
    }
    public void delete(Long categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException("찾으시는 category 정보: " + categoryId + "가 존재하지 않습니다."));

        categoryRepository.delete(category);


    }


}
