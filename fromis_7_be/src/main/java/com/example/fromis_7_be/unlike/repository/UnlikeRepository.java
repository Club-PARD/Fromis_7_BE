package com.example.fromis_7_be.unlike.repository;


import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.unlike.entity.Unlike;
import com.example.fromis_7_be.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnlikeRepository extends JpaRepository<Unlike, Integer> {

    Optional<Unlike> findByUserAndListup(User user, Listup listup);

}
