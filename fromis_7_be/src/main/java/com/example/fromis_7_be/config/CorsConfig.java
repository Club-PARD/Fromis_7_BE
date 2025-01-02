package com.example.fromis_7_be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");  // 특정 Origin만 허용
        config.addAllowedOrigin("https:// https://fromis7-149e7.web.app");  // 특정 Origin만 허용
        config.addAllowedHeader("*"); // 모든 Header 허용
        config.addAllowedMethod("*"); // 모든 HTTP Method 허용
        source.registerCorsConfiguration("/**", config);  // 모든 경로에 적용
        return new CorsFilter(source);
    }
}

