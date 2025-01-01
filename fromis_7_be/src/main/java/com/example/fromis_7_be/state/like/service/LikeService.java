package com.example.fromis_7_be.state.like.service;

import com.example.fromis_7_be.listup.dto.ListupResponse;
import com.example.fromis_7_be.state.like.dto.LikeRequest;
import com.example.fromis_7_be.state.like.dto.LikeResponse;
import com.example.fromis_7_be.state.like.entity.Like;
import com.example.fromis_7_be.state.like.repository.LikeRepository;
import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.listup.repository.ListupRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final ListupRepository listupRepository;
    private final UserRepository userRepository;

    @Transactional
    public LikeResponse.LikeRead createLike(Long userId, Long listupId) {
        // 좋아요 대상 조회
        Listup listup = listupRepository.findById(listupId)
                .orElseThrow(() -> new IllegalArgumentException("Listup not found with id: " + listupId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 기존 좋아요 확인
        Like existingLike = likeRepository.findByUserAndListup(user, listup).orElse(null);

        if (existingLike != null) {  // 좋아요 취소
            // 색상 업데이트
            existingLike.updateColor("#dcdcdc");  // 원하는 색상으로 변경
            likeRepository.delete(existingLike);
            // 삭제된 좋아요 정보를 반환
            return LikeResponse.LikeRead.form(
                    existingLike.getId(),
                    existingLike.getColor(),
                    false // 좋아요 취소 상태
            );
        } else {  // 좋아요 추가
            Like like = Like.form(user ,listup, "#5BA8FB");
            likeRepository.save(like);

            // 추가된 좋아요 정보를 반환
            return LikeResponse.LikeRead.form(
                    like.getId(),
                    like.getColor(),
                    true // 좋아요 상태
            );
        }
    }
}

