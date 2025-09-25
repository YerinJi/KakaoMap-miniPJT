package com.multi.kakaomapminipjt.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;

@Configuration
public class SecurityConfig {
    private static final String[] PERMIT_ALL_PATHS = {
            "/",
            "/index.html",
            "/kakao-ok.html",
            "/index-teacher-*.html",
            "/**/*.html",
            "/css/**",
            "/js/**",
            "/images/**",
            "/webjars/**"
    };

    @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(getPermitAllMatchers()).permitAll()
                        .anyRequest().permitAll()
                )
                .headers(headers -> headers.addHeaderWriter(
                        new StaticHeadersWriter(
                                "Content-Security-Policy",
                                "default-src * data: blob: 'unsafe-inline' 'unsafe-eval'"
                        )
                ));
        return http.build();
    }

    private AntPathRequestMatcher[] getPermitAllMatchers() {
        return Arrays.stream(PERMIT_ALL_PATHS)
                .map(AntPathRequestMatcher::new)
                .toArray(AntPathRequestMatcher[]::new);
    }
}
