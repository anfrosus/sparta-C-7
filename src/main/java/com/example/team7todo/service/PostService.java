package com.example.team7todo.service;

import com.example.team7todo.config.UserDetailsImpl;
import com.example.team7todo.dto.request.PostRequestDto;
import com.example.team7todo.dto.response.PostResponseDto;
import com.example.team7todo.dto.response.ResponseDto;
import com.example.team7todo.handler.customexception.DataNotFoundException;
import com.example.team7todo.handler.customexception.NotAuthorException;
import com.example.team7todo.model.Comment;
import com.example.team7todo.model.Post;
import com.example.team7todo.repository.CommentRepository;
import com.example.team7todo.repository.LikeRepository;
import com.example.team7todo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;


    @Transactional(readOnly = true)
    //게시글 조회
    public ResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("게시글 조회", "해당 데이터가 존재하지 않습니다.")
        );
        Integer commentsCount = commentRepository.countByPost(post);
        Integer likesCount = likeRepository.countByPost(post);

        return ResponseDto.success(new PostResponseDto(post, commentsCount, likesCount));
    }

    //게시글 전체 조회
    @Transactional(readOnly = true)
    public ResponseDto getPosts() {
        List<Post> allPosts = postRepository.findAll();
        List<PostResponseDto> listAll = new ArrayList<>();

        for (Post post : allPosts) {
            listAll.add(new PostResponseDto(post, commentRepository.countByPost(post), likeRepository.countByPost(post)));
        }
        return ResponseDto.success(listAll);
    }

    //게시글 작성
    @Transactional
    public ResponseDto createPost(PostRequestDto postRequestDto, UserDetailsImpl userDetails) {
        Post post = new Post(postRequestDto, userDetails.getMember());
        postRepository.save(post);
        return ResponseDto.success(new PostResponseDto(post, commentRepository.countByPost(post), likeRepository.countByPost(post)));
    }


    //게시글 수정
    @Transactional
    public ResponseDto updatePost(Long id, PostRequestDto postRequestDto, UserDetailsImpl userDetails) {

        if (id == userDetails.getMember().getId()) {
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new DataNotFoundException("게시글 수정", "해당 데이터가 존재하지 않습니다.")
            );
            post.update(postRequestDto);
            return ResponseDto.success(new PostResponseDto(post, commentRepository.countByPost(post), likeRepository.countByPost(post)));
        } else {
            throw new NotAuthorException("게시글 수정", "작성자만 수정이 가능합니다.");
        }
    }

    //게시글 삭제
    @Transactional
    public ResponseDto deletePost(Long id, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(id).orElseThrow(() -> new DataNotFoundException("게시글 삭제", "해당 게시글이 존재하지 않습니다."));
        if (post.getMember().getId() == userDetails.getMember().getId()) {
            postRepository.deleteById(id);
            return ResponseDto.success(id + "게시글 삭제 완료");
        } else {
            throw new NotAuthorException("게시글 삭제", "작성자만 삭제가 가능합니다.");
        }
    }


}
