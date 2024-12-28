package com.example.fromis_7_be.googlelogin.repo;

import com.example.fromis_7_be.googlelogin.entity.GoogleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<GoogleUser,Long> {

    GoogleUser findByUsername(String username);

}
