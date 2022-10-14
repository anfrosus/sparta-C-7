package com.example.team7todo.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter @Setter
@NoArgsConstructor
public class MemberRequestDto {

    //Valid @Pattern()
    private String email;

    //Valid @Pattern()
    private String password;

    //Valid @Pattern()
    private String doubleCheck;
}
