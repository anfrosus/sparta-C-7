package com.example.team7todo.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class LoginRequestDto {

    @NotBlank
    String email;

    @NotBlank
    String password;
}
