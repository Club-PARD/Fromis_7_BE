package com.example.fromis_7_be.user.repository;

import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.userpiece.entity.UserPiece;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @OneToMany(mappedBy = "piece", cascade = CascadeType.ALL, orphanRemoval = true)
    List<UserPiece> userPieces = new ArrayList<>();

    Optional<User> findByEmail(String email);
}
