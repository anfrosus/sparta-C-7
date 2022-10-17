package com.example.team7todo.dto.response;

import com.example.team7todo.model.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentResponseDto {
    private Long postId;
    private String comment;

    public CommentResponseDto(Comment comment) {
        this.postId = comment.getPost().getId();
        this.comment = comment.getComment();
    }
}
