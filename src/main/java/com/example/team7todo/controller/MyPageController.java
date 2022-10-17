package com.example.team7todo.controller;


import com.example.team7todo.config.UserDetailsImpl;
import com.example.team7todo.dto.response.ResponseDto;
import com.example.team7todo.repository.PostRepository;
import com.example.team7todo.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("api/test")
    public void a () {
        System.out.println("도착");
    }

    @GetMapping("/mypost")
    public ResponseDto getMyPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return myPageService.getMyPosts(userDetails);
    }

    @GetMapping("/mycomment")
    public ResponseDto getMyComments(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return myPageService.getMyComments(userDetails);
    }

    @GetMapping("/mylike")
    public ResponseDto getMyPostsOfLike(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return myPageService.getMyLikePosts(userDetails);
    }
}
