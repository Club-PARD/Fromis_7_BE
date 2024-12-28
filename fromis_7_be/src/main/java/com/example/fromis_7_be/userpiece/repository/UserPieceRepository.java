package com.example.fromis_7_be.userpiece.repository;

import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.userpiece.entity.UserPiece;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPieceRepository extends JpaRepository<UserPiece, Long> {
    List<UserPiece> findAllByUser(User user);
}
