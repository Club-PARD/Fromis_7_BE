package com.example.fromis_7_be.comment.controller;

import com.example.fromis_7_be.comment.dto.CommentRequest;
import com.example.fromis_7_be.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{listtId}")
    public void createComment(@PathVariable Long listId,
                              @PathVariable Long userId,
                              @RequestBody CommentRequest.CommentCreateRequest req){
        commentService.createComment(listId, userId, req);
    }



}
