package com.trip.planit.config;

import com.trip.planit.jwt.JwtAccessDeniedHandler;
import com.trip.planit.jwt.JwtAuthenticationEntryPoint;
import com.trip.planit.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint; // 401
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler; // 403

    private static final String[] PERMIT_URL_ARRAY = {
            // 권한 설정 없이 접근을 허용하는 endpoint
            "/users/*"
    };

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 비밀번호 암호화를 위해 PasswordEncoder은 BCryptPasswordEncorder 사용
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        // Spring Security를 적용하지 않을 리소스 설정
        return (web) -> web.ignoring()
                .antMatchers(
                        "/favicon.ico",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/error",
                        "/api/v3/api-docs/**",
                        "/api/api-docs/**",
                        "/api/swagger-ui/**",
                        "/api/error",
                        "/api/**/n/**"
                );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // WebSecurityConfigurerAdapter 지원하지 않음으로, 생성하는 filter Chain
        // SecurityFilterChain는 여러 보안 필터가 어떤 순서대로 실행될지를 결정 -> 보안 관련한 요청 처리 담당
        return http
                // token을 사용하는 방식이기 때문에 csrf => disable
                .csrf().disable()
                // Security 권한으로 인한 에러 핸들링
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                // [이건 무슨의미인지 알아봐야함]
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                // 세션을 사용하지 않기 때문에 STATELESS 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 접근제한
                .and()
                .authorizeRequests()   // HttpServletRequest을 사용하는 요청들에 대해 접근 제한 설정
                .antMatchers(PERMIT_URL_ARRAY).permitAll() // 접근제어 허용
                .antMatchers().permitAll() // 인증 없이 접근 허용
                .anyRequest().authenticated()                   // 나머지 인증 필요
                .and().build();
    }
}
