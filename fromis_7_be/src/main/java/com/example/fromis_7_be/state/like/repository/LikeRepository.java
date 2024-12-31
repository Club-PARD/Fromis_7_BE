package com.example.fromis_7_be.state.like.repository;

import com.example.fromis_7_be.state.like.entity.Like;
import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {

    Optional<Like> findByUserAndListup(User user, Listup listup);

}
