package com.example.team7todo.repository;

import com.example.team7todo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByMemberEmail(String email);
    Optional<Comment> findCommentByPostIdAndMemberId(Long postId, Long memberId);
}
