package com.example.team7todo.jwt;

import com.example.team7todo.dto.response.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 필터에서 토큰 인증 로직
 * */

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override                       //서블릿에서 만들어놓은 객체들(요청을 받아올 때 미리 response 객체 생성함)
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //request 의 헤더를 가져와서 키값으로 accessToken 꺼내오기
        //재발급 할 때 accessToken 이 없다면 else if 만 타게 되면서??? 아니 access 토큰 유효하지 않을 때 재발급 해줘야하는거아님?
        String accessToken = jwtUtil.getTokenFromHeader(request, "Access");
        String refreshToken = jwtUtil.getTokenFromHeader(request, "Refresh");

        if (accessToken != null) {
            if (!jwtUtil.validateAccessToken(accessToken)){
                //access 토큰이 유효하지 않으면 끝
                jwtExceptionHandler(response, "AccessToken 이 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
                return;
            }
            setAuthentication(jwtUtil.getEmailFromToken(accessToken));
        }else if(refreshToken != null) {
            if (!jwtUtil.validateRefreshToken(refreshToken)){
                //refresh 토큰이 유효하지 않으면 끝 (사실 기간 체크하고 access 재발급 해줘야 하지 않나?) -> 검증이 완료되었다면 재발급 해 주는 api만들어서 해결?
                jwtExceptionHandler(response, "RefreshToken 이 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
                return;
            }
            setAuthentication(jwtUtil.getEmailFromToken(refreshToken));
        }
        //다음 필터로 넘겨주기
        filterChain.doFilter(request,response);
    }
    //토큰이 검증 되었을 때 인증 객체 만들어주기
    public void setAuthentication (String email) {
        Authentication authentication = jwtUtil.createAuthentication(email);
        //security 가 컨텍스트에 접근해서 인증 객체가 있는지 없는지 확인하기 떄문에 다음 필터가 돌 때 시큐리티가 인증객체로 인지
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void jwtExceptionHandler(HttpServletResponse response, String msg, HttpStatus status) {
        response.setStatus(status.value());
        response.setContentType("application/json");
        try {
            String json = new ObjectMapper().writeValueAsString(ResponseDto.fail(response.getStatus()+"", msg));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
