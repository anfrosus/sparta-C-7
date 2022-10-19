package com.example.team7todo.service;

import com.example.team7todo.config.UserDetailsImpl;
import com.example.team7todo.dto.response.ResponseDto;
import com.example.team7todo.handler.customexception.DataNotFoundException;
import com.example.team7todo.handler.customexception.NotAuthorException;
import com.example.team7todo.model.Like;
import com.example.team7todo.model.Member;
import com.example.team7todo.model.Post;
import com.example.team7todo.repository.LikeRepository;
import com.example.team7todo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

//    @Transactional
//    public ResponseDto doLike2(Long postId, Member currentMember) {
//        Optional<Like> likeOrNot = likeRepository.findByPostIdAndMemberId(postId, currentMember.getId());
//        if (!likeOrNot.isPresent()){
//            Post post = postRepository.findById(postId).orElseThrow(() -> new DataNotFoundException("좋아요", "해당 게시글을 찾을 수 없습니다."));
//            likeRepository.save(new Like(true, currentMember, post));
//            return ResponseDto.success("좋아요 완료");
//        }else {
//            throw new NotAuthorException("좋아요", "이미 해당게시글을 좋아요 했습니다.");
//        }
//    }

    //exists 로 굳이 꺼내오지 않음.
    @Transactional
    public ResponseDto doLike(Long postId, Member currentMember) {
        if(!likeRepository.existsByPostIdAndMemberId(postId, currentMember.getId())){
            Post post = postRepository.findById(postId).orElseThrow(() -> new DataNotFoundException("좋아요", "해당 게시글을 찾을 수 없습니다."));
            likeRepository.save(new Like(true, currentMember, post));
            return ResponseDto.success("좋아요 완료");
        }else {
            throw new NotAuthorException("좋아요", "이미 해당 게시글을 좋아요 했습니다.");
        }
    }

}
