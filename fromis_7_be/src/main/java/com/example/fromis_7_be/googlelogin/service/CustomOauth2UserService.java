package com.example.fromis_7_be.googlelogin.service;

import com.example.fromis_7_be.googlelogin.dto.CustomOAth2USer;
import com.example.fromis_7_be.googlelogin.dto.GoogleResponse;
import com.example.fromis_7_be.googlelogin.dto.GoogleUserDTO;
import com.example.fromis_7_be.googlelogin.dto.OAuth2Response;
import com.example.fromis_7_be.googlelogin.entity.GoogleUser;
import com.example.fromis_7_be.googlelogin.repo.UserRepo;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepo userRepo;

    public CustomOauth2UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {
            return null;
        }
        String username = oAuth2Response.getProvider()+""+oAuth2Response.getProviderId();

        GoogleUser existData = userRepo.findByUsername(username);

        if (existData == null) {

            GoogleUser userEntity = new GoogleUser();
            userEntity.setUsername(username);
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setName(oAuth2Response.getName());
            userEntity.setRole("ROLE_USER");

            userRepo.save(userEntity);

            GoogleUserDTO userDTO = new GoogleUserDTO();
            userDTO.setUsername(username);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole("ROLE_USER");

            return new CustomOAth2USer(userDTO);
        }
        else {

            existData.setEmail(oAuth2Response.getEmail());
            existData.setName(oAuth2Response.getName());

            userRepo.save(existData);

            GoogleUserDTO userDTO = new GoogleUserDTO();
            userDTO.setUsername(existData.getUsername());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(existData.getRole());

            return new CustomOAth2USer(userDTO);
        }
    }
}
