package com.trip.planit.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 필요한 권한이 존재하지 않는 경우 403 FORBIDDEN 에러를 리턴하는 메서드
        log.info("[403 FORBIDDEN] 잘못된 권한입니다.");
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}
