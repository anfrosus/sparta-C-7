package com.example.team7todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LIKES")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean isLike;

    @ManyToOne
    @JoinColumn(name = "memberId1", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "postId1", nullable = false)
    private Post post;
}
