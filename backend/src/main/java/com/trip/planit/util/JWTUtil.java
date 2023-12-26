package com.trip.planit.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import java.util.Base64;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("jwt.key")
    private String jwtKey; // secret key

    @Value("${jwt.accesstoken.expiretime}")
    private Long accessTokenExpireTime;

    @Value("${jwt.refreshtoken.expiretime}")
    private Long refreshTokenExpireTime;


    public String createAccessToken(String userId) throws UnsupportedEncodingException{
        // 토큰 생성하는 메서드

        // 현재 시간 저장
        long currentTimeMills = System.currentTimeMillis();

        // accessToken 생성
        return Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(new Date(currentTimeMills))
                .setExpiration(new Date(currentTimeMills + accessTokenExpireTime * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtKey.getBytes("UTF-8"))
                .compact();
    }

    public String createRefreshToken(String userId) throws UnsupportedEncodingException{
        // refreshToken 생성

        long currentTimeMills = System.currentTimeMillis();

        // refreshToken 생성
        return Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(new Date(currentTimeMills))
                .setExpiration(new Date(currentTimeMills + refreshTokenExpireTime * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtKey.getBytes("UTF-8"))
                .compact();
    }

    public String getUserId(String authorization) throws ParseException {
        // access Token 파싱
        String [] chunks = authorization.split("\\.");
        Base64.Decoder decorder = Base64.getUrlDecoder();

        // payload에 저장된 userId를 획득
        String payload = new String(decorder.decode(chunks[1]));
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(payload);

        String userId = (String) obj.get("userId");

        return userId;
    }

    public boolean validCheck(String token)  {

        // 해당 토큰을 확인하면서 예외가 발생하는 경우
        // 만료되었거나, 잘못된 토큰이다.
        try{
            Jwts.parser().setSigningKey(jwtKey.getBytes("UTF-8")).parseClaimsJws(token);
        }
        catch (Exception e){
            // 예외가 발생한 경우 유효한 토큰이 아니다.
            return false;
        }
        // 예외가 발생하지 않았다면 유효한 토큰이다.
        return true;
    }




}
