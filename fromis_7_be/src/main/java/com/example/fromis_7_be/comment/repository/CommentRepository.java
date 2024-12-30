package com.example.fromis_7_be.comment.repository;

import com.example.fromis_7_be.comment.entity.Comment;
import com.example.fromis_7_be.listup.entity.Listup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
