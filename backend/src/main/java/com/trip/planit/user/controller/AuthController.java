package com.trip.planit.user.controller;

import com.trip.planit.user.model.dto.Auth;
import com.trip.planit.user.model.service.AuthService;
import com.trip.planit.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthController {

    @Value("${jwt.accesstoken.expiretime}")
    private int accessTokenExpireTime;

    @Value("${jwt.refreshtoken.expiretime}")
    private int refreshTokenExpireTime;

    private JWTUtil jwtUtil;
    private AuthService authService;

    @PostMapping("/slient-refresh")
    public ResponseEntity<Map<String, Object>> slientRefresh(HttpServletRequest request,
                                                             HttpServletResponse response) throws ParseException, UnsupportedEncodingException {
        // slientRefresh가 발생하는 경우, HttpOnly Cookie를 가져온다.
        Cookie[] cookieList = request.getCookies();

        String refreshToken = "";

        for (Cookie cookie : cookieList){
            if (cookie.getName().equals("refreshToken")){
                refreshToken = cookie.getValue();
            }
        }

        String userId = jwtUtil.getUserId(refreshToken);
        String dbRefreshToken = authService.getRefreshToken(userId).getRefreshToken();

        Map<String, Object> result = new HashMap<String, Object>();

        // cookie에 담겨있는 refreshToken을 검증하고
        boolean isValid = jwtUtil.validCheck(refreshToken);

        // 요청으로 넘어온 refreshToken과 database에 저장된 refreshToken이 다른 경우
        if (!refreshToken.equals(dbRefreshToken)){
            isValid = false;
        }

        // 유효하지 않은 경우에는 재 로그인 진행
        if (!isValid){
            result.put("message", "로그인이 필요합니다.");
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.FORBIDDEN);
        }

        // 유효한 경우에는 refreshToken과 accessToken을 재발급
        String newAccessToken = jwtUtil.createAccessToken(userId);
        String newRefreshToken = jwtUtil.createRefreshToken(userId);

        // REfreshToken은 HttpOnly Cookie로 발급
        Cookie cookie = new Cookie("refreshToken", newRefreshToken);
        cookie.setMaxAge(refreshTokenExpireTime);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        // 새로 발급 받은 refreshToken을 데이터 베이스에 저장
        Auth auth = new Auth(userId, newRefreshToken);
        authService.updateRefreshToken(auth);

        result.put("Authorization", newAccessToken);

        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }




}
