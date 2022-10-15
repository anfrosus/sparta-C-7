package com.example.team7todo.dto.request;

import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
public class MemberRequestDto {

    //Valid @Pattern()
    private String email;

    private String nickname;

    //Valid @Pattern()
    private String password;

    //Valid @Pattern()
    private String doubleCheck;
}
