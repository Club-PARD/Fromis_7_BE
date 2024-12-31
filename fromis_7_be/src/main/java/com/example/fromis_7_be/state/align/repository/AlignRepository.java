package com.example.fromis_7_be.state.align.repository;

import com.example.fromis_7_be.state.align.entity.Align;
import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlignRepository extends JpaRepository<Align, Integer> {

    Optional<Align> findByUserAndListup(User user, Listup listup);

}
