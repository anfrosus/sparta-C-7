package com.example.team7todo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class CommentRequestDto {

    @NotBlank(message = "댓글을 입력해 주세요")
    private String comment;
}
