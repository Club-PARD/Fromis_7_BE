package com.example.fromis_7_be.listup.repository;

import com.example.fromis_7_be.category.entity.Category;
import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListupRepository extends JpaRepository<Listup, Long> {
    List<Listup> findAllByCategory(Category category);
}
