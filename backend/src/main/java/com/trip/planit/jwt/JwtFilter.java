package com.trip.planit.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;




@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private static final String AUTHORIZATION_HEADER = "Authorization"; // token header
    private final JwtProvider jwtProvider; // jwt 생성 및 검증 모듈

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = resolveToken(request); // request의 header에서 token 파싱
        String requestURI = request.getRequestURI();

        if (StringUtils.hasText(token) && jwtProvider.validateToken(token)) {
            // token이 존재하고, 검증되었을 경우
            Authentication auth = jwtProvider.getAuthentication(token); // 인증 확인
            SecurityContextHolder.getContext().setAuthentication(auth); // Security Context에 인증정보 저장
            log.debug("Security Context에 '{}' 인증정보를 저장했습니다. - uri : {} ", auth.getName(), requestURI );
        } else{
            log.debug("유효한 JWT 토큰이 없습니다 - uri : {}", requestURI);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public String resolveToken(HttpServletRequest request){
        //   Request Header에서 token 정보를 꺼내기 위한 메서드
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

}
