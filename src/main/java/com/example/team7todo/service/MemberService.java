package com.example.team7todo.service;

import com.example.team7todo.domain.Member;
import com.example.team7todo.dto.request.MemberRequestDto;
import com.example.team7todo.dto.response.MemberResponseDto;
import com.example.team7todo.dto.response.ResponseDto;
import com.example.team7todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseDto<?> createMember(MemberRequestDto memberRequestDto) {
        // 검증 로직 및 예외처리 작성
        //저장
        Member member = Member.builder()
                .email(memberRequestDto.getEmail())
                .password(passwordEncoder.encode(memberRequestDto.getPassword()))
                .nickname(memberRequestDto.getNickname())
                .build();

        memberRepository.save(member);
        System.out.println("여긴오나?");
        return ResponseDto.success(
                MemberResponseDto.builder()
                        .id(member.getId())
                        .email(member.getEmail())
                        .nickName(member.getNickname())
                        .createAt(member.getCreateAt())
                        .modifiedAt(member.getModifiedAt())
                        .build()
        );
    }
}
