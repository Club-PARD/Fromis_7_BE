package com.example.fromis_7_be.piece.repository;

import com.example.fromis_7_be.piece.entity.Piece;
import com.example.fromis_7_be.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PieceRepository extends JpaRepository<Piece, Long> {
    @Query("SELECT p FROM Piece p JOIN p.userPieces up WHERE up.user = :user")
    List<Piece> findAllByUser(@Param("user") User user);
    //PieceRepository에서 findAllByUser 대신 findAllByUserPieceUser 같은 메서드를 정의하여 JPA가 관계를 정확히 인식
}
