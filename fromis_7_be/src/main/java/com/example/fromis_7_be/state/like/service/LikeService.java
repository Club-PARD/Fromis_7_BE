package com.example.fromis_7_be.state.like.service;

import com.example.fromis_7_be.listup.dto.ListupResponse;
import com.example.fromis_7_be.state.like.dto.LikeRequest;
import com.example.fromis_7_be.state.like.dto.LikeResponse;
import com.example.fromis_7_be.state.like.entity.Like;
import com.example.fromis_7_be.state.like.repository.LikeRepository;
import com.example.fromis_7_be.state.unlike.entity.Unlike;
import com.example.fromis_7_be.state.unlike.repository.UnlikeRepository;
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
    public boolean createLike(Long userId, Long listupId) {
        Optional<Listup> l = listupRepository.findById(listupId);
        Listup listup = l.get();
        Optional<User> u = userRepository.findById(userId);
        User user = u.get();

        Like existingUnlike = likeRepository.findByUserAndListup(user ,listup ).orElse(null);

        if (existingUnlike != null) {  // 이미 좋아요가 존재하면 취소
            likeRepository.delete(existingUnlike);
            return false;
        }
        else {
            Like unlike = Like.form(user, listup);
            likeRepository.save(unlike);
            return true;
        }
    }
}
