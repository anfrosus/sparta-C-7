package com.example.team7todo.controller;

import com.example.team7todo.config.UserDetailsImpl;
import com.example.team7todo.dto.request.LoginRequestDto;
import com.example.team7todo.dto.request.MemberRequestDto;
import com.example.team7todo.dto.response.ResponseDto;
import com.example.team7todo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @PostMapping("/signup")
    public ResponseDto<?> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.createMember(memberRequestDto);
    }

    //로그인
    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return memberService.loginMember(loginRequestDto, response);
    }

    //재발급 (인증은 이미 필터에서 끝났음)
    /**
     * 자동발급으로 대체
     * */
//    @GetMapping("/reIssue")
//    public ResponseDto<?> reissueToken(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response) {
//        return memberService.reissue(userDetails, response);
//    }


}
