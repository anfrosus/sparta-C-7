package com.example.team7todo.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class LoginRequestDto {

    @NotBlank(message = "아이디를 입력해 주세요")
    private String email;

    @NotBlank(message = "패스워드를 입력해 주세요")
    private String password;
}
