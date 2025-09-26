package com.multi.kakaomapminipjt.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/index.html"),
                                new AntPathRequestMatcher("/kakao-ok.html"),
                                new AntPathRequestMatcher("/index-teacher-*.html"),
                                new AntPathRequestMatcher("/**/*.html"),
                                new AntPathRequestMatcher("/css/**"),
                                new AntPathRequestMatcher("/js/**"),
                                new AntPathRequestMatcher("/images/**"),
                                new AntPathRequestMatcher("/webjars/**")
                        ).permitAll()
                        .anyRequest().permitAll()
                )
                // ⚠️ 개발용: CSP를 매우 느슨하게 “한 줄”로 **강제** (이 한 줄만 남기고 다른 CSP 설정은 제거)
                .headers(h -> h.addHeaderWriter(new StaticHeadersWriter(
                        "Content-Security-Policy",
                        "default-src * data: blob: 'unsafe-inline' 'unsafe-eval'"
                )));

        return http.build();
    }
}
