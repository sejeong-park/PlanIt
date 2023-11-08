package com.trip.planit.config;

import com.trip.planit.jwt.JwtFilter;
import com.trip.planit.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtProvider jwtProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // SecurityFilter를 거치기 전에, jwtFilter를 설정해주기 위한 메서드
        http.addFilterBefore(
                new JwtFilter(jwtProvider),
                UsernamePasswordAuthenticationFilter.class
        );
    }
}
