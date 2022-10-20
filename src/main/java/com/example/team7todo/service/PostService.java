package com.example.team7todo.service;

import com.example.team7todo.dto.request.PostRequestDto;
import com.example.team7todo.dto.response.PostResponseDto;
import com.example.team7todo.dto.response.ResponseDto;
import com.example.team7todo.handler.customexception.DataNotFoundException;
import com.example.team7todo.handler.customexception.NotAuthorException;
import com.example.team7todo.model.Member;
import com.example.team7todo.model.Post;
import com.example.team7todo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final EntityManager em;



    @Transactional(readOnly = true)
    //게시글 조회
    public ResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("게시글 조회", "해당 데이터가 존재하지 않습니다.")
        );
        return ResponseDto.success(new PostResponseDto(post));
    }

    //게시글 전체 조회
    @Transactional(readOnly = true)
    public ResponseDto getPosts() {
        List<Post> allPosts = postRepository.findAll();
        List<PostResponseDto> listAll = new ArrayList<>();

        for (Post post : allPosts) {
            listAll.add(new PostResponseDto(post));
        }
        return ResponseDto.success(listAll);
    }

    //게시글 작성
    @Transactional
    public ResponseDto createPost(PostRequestDto postRequestDto, Member currentMember) {
        Post post = new Post(postRequestDto, currentMember);
        postRepository.save(post);
        return ResponseDto.success(new PostResponseDto(post));
    }


    //게시글 수정
    @Transactional
    public ResponseDto updatePost(Long postId, PostRequestDto postRequestDto, Long currentMemberId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new DataNotFoundException("게시글 수정", "해당 게시글이 존재하지 않습니다.")
        );
        //작성자의 아이디 가져와야지
        if (post.getMember().getId().equals(currentMemberId)) {
            post.update(postRequestDto);
            return ResponseDto.success(new PostResponseDto(post));
        } else {
            throw new NotAuthorException("게시글 수정", "작성자만 수정이 가능합니다.");
        }
    }

    //게시글 삭제
    @Transactional
    public ResponseDto deletePost(Long id, Long currentMemberId) {
        Post post = postRepository.findById(id).orElseThrow(() -> new DataNotFoundException("게시글 삭제", "해당 게시글이 존재하지 않습니다."));

        if (post.getMember().getId().equals(currentMemberId)) {
            postRepository.deleteById(id);
            return ResponseDto.success(id + "게시글 삭제 완료");
        } else {
            throw new NotAuthorException("게시글 삭제", "작성자만 삭제가 가능합니다.");
        }
    }


}
