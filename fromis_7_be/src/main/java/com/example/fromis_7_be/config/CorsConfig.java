package com.example.fromis_7_be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // CORS 설정
        config.addAllowedOrigin("http://localhost:3000"); // 프론트엔드 도메인 허용
        config.addAllowedOrigin("https://fromis7.store"); // 배포된 도메인 허용
        config.addAllowedHeader("*"); // 모든 헤더 허용
        config.addAllowedMethod("*"); // 모든 HTTP 메소드 허용 (GET, POST, PUT 등)

        // 모든 경로에 대해 위 설정 적용
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
