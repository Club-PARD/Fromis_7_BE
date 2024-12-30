package com.example.fromis_7_be.like.service;

import com.example.fromis_7_be.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import com.example.fromis_7_be.like.repository.LikeRepository;
import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.listup.repository.ListupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final ListupRepository listupRepository;

}
