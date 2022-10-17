package com.example.team7todo.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PostRequestDto {

    @NotBlank(message = "제목을 입력해 주세요")
    private String title;

    @NotBlank(message = "내용을 입력해 주세요")
    private String content;
}
