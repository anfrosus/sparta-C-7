package com.example.team7todo.repository;

import com.example.team7todo.model.Like;
import com.example.team7todo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findLikesByMemberEmail (String email);

    Optional<Like> findByPostIdAndMemberId(Long PostId, Long MemberId);
}
