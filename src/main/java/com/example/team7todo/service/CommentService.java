package com.example.team7todo.service;

import com.example.team7todo.config.UserDetailsImpl;
import com.example.team7todo.dto.request.CommentRequestDto;
import com.example.team7todo.dto.response.CommentResponseDto;
import com.example.team7todo.dto.response.ResponseDto;
import com.example.team7todo.handler.customexception.DataNotFoundException;
import com.example.team7todo.handler.customexception.NotAuthorException;
import com.example.team7todo.model.Comment;
import com.example.team7todo.model.Post;
import com.example.team7todo.repository.CommentRepository;
import com.example.team7todo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    //댓글 작성
    @Transactional
    public ResponseDto createComment(Long postId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new DataNotFoundException("댓글작성", "존재하지 않는 회원입니다."));
        Comment comment = new Comment(commentRequestDto, post, userDetails.getMember());
        commentRepository.save(comment);
        return ResponseDto.success(new CommentResponseDto(comment));
    }

    //댓글 수정
    @Transactional
    public ResponseDto updateComment(Long postId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {

        Comment comment = commentRepository.findCommentByPostIdAndMemberId(postId, userDetails.getMember().getId())
                .orElseThrow(() -> new DataNotFoundException("댓글 수정", "조건에 해당하는 댓글을 찾을 수 없습니다."));

        if (comment.getMember().getEmail() == userDetails.getMember().getEmail()) {
            comment.update(commentRequestDto);
            return ResponseDto.success(new CommentResponseDto(comment));
        }else {
            throw new NotAuthorException("댓글 수정", "댓글 수정 권한이 없습니다.");
        }
    }

    //댓글 삭제
    @Transactional
    public ResponseDto deleteComment(Long postId, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findCommentByPostIdAndMemberId(postId, userDetails.getMember().getId())
                .orElseThrow(() -> new DataNotFoundException("댓글 삭제", "조건에 해당하는 댓글을 찾을 수 없습니다."));

        if (comment.getMember().getId() == userDetails.getMember().getId()) {
            commentRepository.deleteById(comment.getId());
            return ResponseDto.success("댓글 삭제 완료");
        }else {
            throw new NotAuthorException("댓글 삭제", "댓글 삭제 권한이 없습니다.");
        }

    }
}