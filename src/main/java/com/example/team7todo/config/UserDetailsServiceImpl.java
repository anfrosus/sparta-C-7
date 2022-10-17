package com.example.team7todo.config;

import com.example.team7todo.handler.customexception.LoginException;
import com.example.team7todo.model.Member;
import com.example.team7todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//UserDetails 에 Member 넣어주기 위해
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;


    //로드바이 유저네임이 생성되는 시점 -> 토큰검증 직후인듯
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new LoginException("로그인", "해당 회원이 존재하지 않습니다.")
        );

        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setMember(member);
        return userDetails;
    }
}
