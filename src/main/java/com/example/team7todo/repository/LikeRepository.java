package com.example.team7todo.repository;

import com.example.team7todo.model.Comment;
import com.example.team7todo.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findLikesByMemberEmail (String email);
}
