package com.example.team7todo.repository;

import com.example.team7todo.model.Like;
import com.example.team7todo.model.Member;
import com.example.team7todo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByPostIdAndMemberId(Long postId, Long memberId);

}
