package com.example.team7todo.service;

import com.example.team7todo.dto.response.MyCommentResponseDto;
import com.example.team7todo.dto.response.PostResponseDto;
import com.example.team7todo.dto.response.ResponseDto;
import com.example.team7todo.handler.customexception.DataNotFoundException;
import com.example.team7todo.model.Comment;
import com.example.team7todo.model.Like;
import com.example.team7todo.model.Member;
import com.example.team7todo.model.Post;
import com.example.team7todo.repository.CommentRepository;
import com.example.team7todo.repository.LikeRepository;
import com.example.team7todo.repository.MemberRepository;
import com.example.team7todo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MemberRepository memberRepository;

    //내가 쓴 글 조회하기
    @Transactional(readOnly = true)
    public ResponseDto getMyPosts(Member currentMember) {
        Member member = memberRepository.findById(currentMember.getId()).orElseThrow(()-> new DataNotFoundException("나의 글", "멤버 조회 불가"));
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for (Post post : member.getPosts()) {
            postResponseDtoList.add(new PostResponseDto(post));
        }
        return ResponseDto.success(postResponseDtoList);
    }

    //내 댓글 조회하기
    @Transactional(readOnly = true)
    public ResponseDto getMyComments(Member currentMember) {
//        System.out.println(currentMember.getComments().size());
        Member member = memberRepository.findById(currentMember.getId()).orElseThrow(() -> new DataNotFoundException("내댓글", "멤버 조회 불가"));
        List<MyCommentResponseDto> myCommentResponseDtoList = new ArrayList<>();
        for (Comment comment : member.getComments()){
            myCommentResponseDtoList.add(new MyCommentResponseDto(comment));
        }
        return ResponseDto.success(myCommentResponseDtoList);
    }

    //내가 좋아요 한 글 조회하기
    @Transactional(readOnly = true)
    public ResponseDto getMyLikePosts(Member currentMember) {
        Member member = memberRepository.findById(currentMember.getId()).orElseThrow(() -> new DataNotFoundException("내 좋아요", "멤버 조회 불가"));
        List<Like> likes = member.getLikes();
//        List<Like> likes2 = likeRepository.findLikesByMemberId(currentMember.getId());
        List<PostResponseDto> responseDtos = new ArrayList<>();
        for (Like like : likes) {
            responseDtos.add(new PostResponseDto(like.getPost()));
        }
        return ResponseDto.success(responseDtos);
    }

}
