package com.example.team7todo.dto.response;

import com.example.team7todo.model.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PostResponseDto {
    private String title;
    private String author;
    private Integer sizeOfLikes;
    private Integer sizeOfComments;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.author = post.getMember().getEmail();
        this.sizeOfLikes = post.getLikes() == null ? 0 : post.getLikes().size();
        this.sizeOfComments = post.getComments() == null ? 0 : post.getComments().size();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
