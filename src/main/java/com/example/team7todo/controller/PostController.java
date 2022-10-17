package com.example.team7todo.controller;

import com.example.team7todo.config.UserDetailsImpl;
import com.example.team7todo.dto.request.PostRequestDto;
import com.example.team7todo.dto.response.ResponseDto;
import com.example.team7todo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    //게시글 하나 조회
    @GetMapping("/{id}")
    public ResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    //게시글 전체 조회
    @GetMapping
    public ResponseDto getPosts(){
        return postService.getPosts();
    }

    @PostMapping
    public ResponseDto createPost(@RequestBody @Valid PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(postRequestDto, userDetails);
    }

    @PutMapping("/{id}")
    public ResponseDto updatePost(@PathVariable Long id, @RequestBody @Valid PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(id, postRequestDto, userDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(id, userDetails);
    }

}
