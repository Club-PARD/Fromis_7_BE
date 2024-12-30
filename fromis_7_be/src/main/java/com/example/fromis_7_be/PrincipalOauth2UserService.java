package com.example.fromis_7_be;

import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser (
            OAuth2UserRequest oAuth2UserRequest
    ) throws OAuth2AuthenticationException {
        log.info("google에서만 받아온 UserRequest: " + oAuth2UserRequest);
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        log.info("oauth에서 받아온 정보: " + oAuth2User.getAttributes());

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        String image = (String) attributes.get("picture");

        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            User newUser = User.builder()
                    .name(name)
                    .email(email)
                    .profileImg(image)
                    .createdAt(now)
                    .modifiedAt(now)
                    .build();

            userRepository.save(newUser);
            log.info("새로운 사용자 정보 저장 완료: " + newUser);
        } else {
            log.info("기존 사용자 로그인: " + existingUser.get());
        }

        return oAuth2User;
    }
}