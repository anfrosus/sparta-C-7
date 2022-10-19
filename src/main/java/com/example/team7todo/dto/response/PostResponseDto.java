package com.example.team7todo.dto.response;

import com.example.team7todo.model.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PostResponseDto {
    private String title;
    private String author;
    private String content;
    private Integer commentsCount;
    private Integer likesCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.author = post.getMember().getEmail();
        this.commentsCount = post.getComments() == null ? 0 : post.getComments().size();
        this.likesCount = post.getLikes() == null ? 0 : post.getLikes().size();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.content = post.getContent();
    }
}
