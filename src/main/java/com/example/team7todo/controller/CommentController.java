package com.example.team7todo.controller;

import com.example.team7todo.config.UserDetailsImpl;
import com.example.team7todo.dto.request.CommentRequestDto;
import com.example.team7todo.dto.response.ResponseDto;
import com.example.team7todo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    //댓글 작성
    @PostMapping("{postId}/comment/")
    public ResponseDto createComment(@PathVariable Long postId, @RequestBody @Valid CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(postId, commentRequestDto, userDetails);
    }

    //댓글 수정
    @PutMapping("{postId}/comment/")
    public ResponseDto updateComment(@PathVariable Long postId, @RequestBody @Valid CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(postId, commentRequestDto, userDetails);
    }

    //댓글 삭제
    @DeleteMapping("{postId}/comment")
    public ResponseDto deleteComment(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(postId, userDetails);
    }
}
