package com.trip.planit.Interceptor;

import com.trip.planit.annotation.AuthRequired;
import com.trip.planit.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final String ACCESSTOKEN_HEADER = "Authorization"; // token header key

    private final JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // handler가 메서드에서 호출된 것인지 확인
        // 그렇지 않으면 모두 패스
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 호출된 메서드의 Annotation이 AuthRequired인지 확인
        if (handlerMethod.getMethodAnnotation(AuthRequired.class) != null ||
                handlerMethod.getBeanType().getAnnotation(AuthRequired.class) != null)
        {
            String accessToken = request.getHeader(ACCESSTOKEN_HEADER);

            // accessToken과 refreshToken을 확인
            if (accessToken != null){
                if (jwtUtil.validCheck(accessToken)){
                    return true;
                }
            }

            // 권한이 없음을 클라이언트에 전송
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        // AuthRequired가 붙지 않은 경우 모두 통과
        return true;
    }


}
