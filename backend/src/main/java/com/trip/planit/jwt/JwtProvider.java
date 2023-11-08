package com.trip.planit.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtProvider {

    // @Value(value = "${custom.jwt-secret-key}")
    public static final String AUTHORITIES_KEY = "auth";
    private static final String jwtSecretKey = "BestPlanIsPlanit"; // secret key
    private final long expiration = 1000L * 60 * 60; // 만료 시간 : 1 hour
    private final long currentTime = System.currentTimeMillis(); // 현재 시간

    public String createToken(String userId) throws Exception{
        // token 생성 로직
        return Jwts.builder()
                .claim("userId", userId) // 정보 저장
                .setIssuedAt(new Date(currentTime)) // 토큰 발행시간 정보
                .setExpiration(new Date(currentTime + expiration)) // 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey.getBytes(StandardCharsets.UTF_8)) // 암호화 알고리즘과 시크릿 키
                .compact();
    }

    public Authentication getAuthentication(String token){
        // 토큰으로 Claim 만들고, 이를 이용해 유저 객체를 만들어서 최종적으로, authentication 객체를 리턴
        Claims claims = Jwts
                .parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    // jwt 토큰에서 회원 구별 정보 추출
    public String getUserId(String token) {
        String result = "";
        result = (String) Jwts.parser().setSigningKey(jwtSecretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody().getSubject();
        return result;
    }


   // jwt 유효성 검사
    public boolean validateToken(String token) {
        try {
                Jwts.parser()
                        .setSigningKey(jwtSecretKey.getBytes(StandardCharsets.UTF_8))
                        .parseClaimsJws(token);
                return true;
        } catch(SecurityException | MalformedJwtException e){
            log.info("잘못된 jwt 서명입니다.");
        } catch(ExpiredJwtException e){
            log.info("만료된 jwt 토큰입니다.");
        } catch(UnsupportedJwtException e){
            log.info("지원되지 않는 jwt 토큰입니다.");
        } catch(IllegalArgumentException e){
            log.info("jwt 토큰이 잘못되었습니다.");
        }
        return false;
    }

}
