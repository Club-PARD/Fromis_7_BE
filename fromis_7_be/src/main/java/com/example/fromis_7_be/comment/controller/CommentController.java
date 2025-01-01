package com.example.fromis_7_be.comment.controller;

import com.example.fromis_7_be.comment.dto.CommentRequest;
import com.example.fromis_7_be.comment.dto.CommentResponse;
import com.example.fromis_7_be.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{listId}/{userId}")
    @Operation(summary = "comment를 생성하기 listId, userId 참조")
    public ResponseEntity<CommentResponse.CommentReadResponse> createComment(@PathVariable Long listId,
                                                                             @PathVariable Long userId,
                                                                             @RequestBody CommentRequest.CommentCreateRequest req){
        CommentResponse.CommentReadResponse createdComment = commentService.createComment(listId, userId, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "comment를 삭제하기, commentId 참조")
    public void deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }

}
