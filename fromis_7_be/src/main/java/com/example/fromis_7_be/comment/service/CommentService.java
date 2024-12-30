package com.example.fromis_7_be.comment.service;

import com.example.fromis_7_be.comment.dto.CommentRequest;
import com.example.fromis_7_be.comment.dto.CommentResponse;
import com.example.fromis_7_be.comment.entity.Comment;
import com.example.fromis_7_be.comment.repository.CommentRepository;
import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.listup.repository.ListupRepository;
import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserRepository userRepository;
    private final ListupRepository listupRepository;
    private final CommentRepository commentRepository;

    public void createComment(Long listupId, Long userId, CommentRequest.CommentCreateRequest req){
        Listup listup = listupRepository.findById(listupId)
                .orElseThrow(() -> new NoSuchElementException("찾으시는 listup 정보: " + listupId + "가 존재하지 않습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("찾으시는 user 정보: " + userId + "가 존재하지 않습니다."));

        Comment comment = Comment.from(listup, user, req.getContent());

        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(IllegalAccessError::new);
        commentRepository.delete(comment);
    }


}
