package com.example.team7todo.repository;

import com.example.team7todo.model.Comment;
import com.example.team7todo.model.Member;
import com.example.team7todo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByMember(Member member);
    Optional<Comment> findCommentByPostIdAndMemberId(Long postId, Long memberId);

    Integer countByPost(Post post);
}
