package com.example.fromis_7_be.googlelogin.repo;

import com.example.fromis_7_be.googlelogin.entity.Refresh;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshRepo extends JpaRepository<Refresh, Long> {

    boolean existsByRefresh(String refresh);

    @Transactional
    void deleteByRefresh(String refresh);
}
