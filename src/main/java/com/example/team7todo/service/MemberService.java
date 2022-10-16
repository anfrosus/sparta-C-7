package com.example.team7todo.service;

import com.example.team7todo.config.UserDetailsImpl;
import com.example.team7todo.domain.Member;
import com.example.team7todo.dto.request.LoginRequestDto;
import com.example.team7todo.dto.request.MemberRequestDto;
import com.example.team7todo.dto.response.MemberResponseDto;
import com.example.team7todo.dto.response.ResponseDto;
import com.example.team7todo.handler.customexception.WrongInputException;
import com.example.team7todo.jwt.JwtUtil;
import com.example.team7todo.domain.RefreshToken;
import com.example.team7todo.jwt.tdto.TokenDto;
import com.example.team7todo.repository.MemberRepository;
import com.example.team7todo.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

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

        //success 의 리턴값이 new ResponseDto 임.
        return ResponseDto.success(
                MemberResponseDto.builder()
                        .id(member.getId())
                        .email(member.getEmail())
                        .nickName(member.getNickname())
                        .createdAt(member.getCreatedAt())
                        .modifiedAt(member.getModifiedAt())
                        .build()
        );
    }

    @Transactional
    public ResponseDto<?> loginMember(LoginRequestDto loginRequestDto, HttpServletResponse response) {

        Member member = memberRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new WrongInputException("존재하지 않는 아이디 입니다.")
        );
        //패스워드 인코더의 매치스함수 이용해서 비밀번호 대조 (boolean 리턴)
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new WrongInputException("비밀번호가 일치하지 않습니다.");
        }

        //인증 된 사람에게 토큰 발급하기
        TokenDto tokenDto = jwtUtil.issueToken(loginRequestDto.getEmail());

        //기존에 리프레쉬 토큰을 가지고 있었다면 갈아끼워주고
        Optional<RefreshToken> savedRefreshToken = refreshTokenRepository.findByEmail(loginRequestDto.getEmail());
        if (savedRefreshToken.isPresent()) {
            refreshTokenRepository.save(savedRefreshToken.get().updateRefreshToken(tokenDto.getRefreshToken()));
        } else {
            //새로 발급된 토큰과 이메일을 담아서 저장하자.
            RefreshToken newRefreshToken = new RefreshToken(tokenDto.getRefreshToken(), loginRequestDto.getEmail());
            refreshTokenRepository.save(newRefreshToken);
        }

        //response 내장함수이용해서 헤더에 각각 토큰 키값과 함께 넣어주기
        setHeader(response, tokenDto);

        return ResponseDto.success(
                MemberResponseDto.builder()
                        .id(member.getId())
                        .email(member.getEmail())
                        .nickName(member.getNickname())
                        .createdAt(member.getCreatedAt())
                        .modifiedAt(member.getModifiedAt())
                        .build()
        );
    }

    /**
     * 자동발급으로 대체
     * */
//    public ResponseDto<?> reissue(UserDetailsImpl userDetails, HttpServletResponse response) {
//        response.addHeader(JwtUtil.ACCESS, jwtUtil.createAccessToken(userDetails.getMember().getEmail()));
//        return ResponseDto.success(
//                "재발급 성공"
//        );
//    }


    private static void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH, tokenDto.getRefreshToken());
    }


}
