package com.example.fromis_7_be.config;

import com.example.fromis_7_be.googlelogin.jwt.CustomLogoutFilter;
import com.example.fromis_7_be.googlelogin.jwt.JWTFilter;
import com.example.fromis_7_be.googlelogin.jwt.JWTUtil;
import com.example.fromis_7_be.googlelogin.oauth2.CustomSuccessHandler;
import com.example.fromis_7_be.googlelogin.repo.RefreshRepo;
import com.example.fromis_7_be.googlelogin.service.CustomOauth2UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOauth2UserService customOauth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final JWTUtil jwtUtil;
    private final RefreshRepo refreshRepo;

    public SecurityConfig(CustomOauth2UserService customOAuth2UserService, CustomSuccessHandler customSuccessHandler, JWTUtil jwtUtil, RefreshRepo refreshRepo) {

        this.customOauth2UserService = customOAuth2UserService;
        this.customSuccessHandler = customSuccessHandler;
        this.jwtUtil = jwtUtil;
        this.refreshRepo = refreshRepo;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       //csrf disable
        http
                .csrf((auth) -> auth.disable());

        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //HTTP Basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

        //JWTFilter 추가
        http
                .addFilterBefore(new JWTFilter(jwtUtil, refreshRepo) , UsernamePasswordAuthenticationFilter.class); // JWT 필터 추가

        http
                .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshRepo), LogoutFilter.class);

        //oauth2
        http
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOauth2UserService))
                        .successHandler(customSuccessHandler));

        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/reissue").permitAll()
                        .anyRequest().authenticated());


        //세션 설정 : STATELESS
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //cors설정
        http
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();

                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);

                        configuration.setExposedHeaders(Collections.singletonList("Set-Cookie"));
                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                        return configuration;
                    }
                }));


        return http.build();
    }
}
