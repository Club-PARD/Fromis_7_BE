package com.example.fromis_7_be.oath.repostory;

import com.example.fromis_7_be.oath.entity.OauthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthRepository extends JpaRepository<OauthUser, Long> {
    Optional<OauthUser> findByEmail(String email);
}
