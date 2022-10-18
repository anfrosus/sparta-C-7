package com.example.team7todo.service;

import com.example.team7todo.config.UserDetailsImpl;
import com.example.team7todo.dto.response.MyCommentResponseDto;
import com.example.team7todo.dto.response.PostResponseDto;
import com.example.team7todo.dto.response.ResponseDto;
import com.example.team7todo.model.Comment;
import com.example.team7todo.model.Like;
import com.example.team7todo.model.Member;
import com.example.team7todo.model.Post;
import com.example.team7todo.repository.CommentRepository;
import com.example.team7todo.repository.LikeRepository;
import com.example.team7todo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    //내가 쓴 글 조회하기
    @Transactional(readOnly = true)
    public ResponseDto getMyPosts(Member currentMember) {
        List<Post> posts = postRepository.findAllByMember(currentMember);
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for (Post post : posts) {
            postResponseDtoList.add(new PostResponseDto(post));
        }
        return ResponseDto.success(postResponseDtoList);
    }

    //내 댓글 조회하기
    @Transactional(readOnly = true)
    public ResponseDto getMyComments(Member currentMember) {
        List<Comment> comments = commentRepository.findAllByMember(currentMember);
        List<MyCommentResponseDto> myCommentResponseDtoList = new ArrayList<>();
        for (Comment comment : comments) {
            myCommentResponseDtoList.add(new MyCommentResponseDto(comment));
        }
        return ResponseDto.success(myCommentResponseDtoList);
    }

    //내가 좋아요 한 글 조회하기
    @Transactional(readOnly = true)
    public ResponseDto getMyLikePosts(Long currentMemberId) {
        List<Like> likes = likeRepository.findLikesByMemberId(currentMemberId);
        List<Post> postsOfLike = new ArrayList<>();
        List<PostResponseDto> responseDtos = new ArrayList<>();

        for (Like like : likes) {
            postsOfLike.add(like.getPost());
        }
        for (Post post : postsOfLike) {
            responseDtos.add(new PostResponseDto(post));
        }
        return ResponseDto.success(responseDtos);
    }

}
