package com.example.team7todo.service;

import com.example.team7todo.model.Member;
import org.aspectj.lang.annotation.RequiredTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    Member member = new Member();

    @Test
    void 회원가입() {
    }
    //우아한형제, 강남언니, != 네이버

    @Test
    void loginMember() {
    }
}