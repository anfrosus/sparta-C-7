package com.example.team7todo.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
public class MemberRequestDto {

    @NotBlank(message = "이메일에 공백이 포함될 수 없습니다.")
    @Pattern(regexp = "^[a-zA-z0-9]+@[a-zA-Z0-9]+\\.[a-z]+$", message = "이메일 형식을 확인해 주세요.")
    private String email;

    @NotBlank(message = "닉네임에 공백이 포함될 수 없습니다.")
    private String nickname;

    @NotBlank(message = "비밀번호에 공백이 포함될 수 없습니다.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-])[a-zA-Z0-9!@#$%^&*()_+=-]*$", message = "영문 소문자, 대문자, 숫자, 특수문자가 반드시 포함되어야 합니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인에 null 이나 공백이 포함될 수 없습니다.")
    private String doubleCheck;
}
