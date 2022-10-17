package com.example.team7todo.repository;

import com.example.team7todo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByMemberEmail(String email);
}
