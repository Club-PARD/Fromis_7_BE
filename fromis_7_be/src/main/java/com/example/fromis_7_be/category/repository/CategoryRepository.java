package com.example.fromis_7_be.category.repository;

import com.example.fromis_7_be.category.entity.Category;
import com.example.fromis_7_be.piece.entity.Piece;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.isHighlighted = true")
    List<Category> findByHighlight(Pageable pageable);

    List<Category> findAllByPiece(Piece piece);

}
