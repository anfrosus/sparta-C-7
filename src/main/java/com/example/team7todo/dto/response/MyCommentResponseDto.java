package com.example.team7todo.dto.response;

import com.example.team7todo.model.Comment;

public class MyCommentResponseDto {
    private String postTitle;
    private String comment;

    public MyCommentResponseDto(Comment comment) {
        this.postTitle = comment.getPost().getTitle();
        this.comment = comment.getComment();
    }
}
