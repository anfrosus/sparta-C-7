package com.example.team7todo.controller;

import com.example.team7todo.config.UserDetailsImpl;
import com.example.team7todo.dto.response.ResponseDto;
import com.example.team7todo.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {

    private final LikeService likeService;
    //몇번글인지, 누구인지
    @GetMapping("/{postId}/like")
    public ResponseDto doLike(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.doLike(postId, userDetails);
    }
}
