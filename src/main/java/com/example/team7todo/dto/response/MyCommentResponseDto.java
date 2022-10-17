package com.example.team7todo.dto.response;

import com.example.team7todo.model.Comment;

public class MyCommentResponseDto {
    String postTitle;
    String comment;

    public MyCommentResponseDto(Comment comment) {
        this.postTitle = comment.getPost().getTitle();
        this.comment = comment.getComment();
    }
}
