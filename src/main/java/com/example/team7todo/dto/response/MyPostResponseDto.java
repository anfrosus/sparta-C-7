package com.example.team7todo.dto.response;

import com.example.team7todo.model.Like;
import com.example.team7todo.model.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class MyPostResponseDto {
    String title;
    Integer sizeOfLikes;
    Integer sizeOfComments;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;

    public MyPostResponseDto(Post post) {
        this.title = post.getTitle();
        this.sizeOfLikes = post.getLikes().size();
        this.sizeOfComments = post.getComments().size();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

}
