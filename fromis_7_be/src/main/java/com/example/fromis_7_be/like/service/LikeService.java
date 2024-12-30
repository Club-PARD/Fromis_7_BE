package com.example.fromis_7_be.like.service;

import com.example.fromis_7_be.like.entity.Like;
import com.example.fromis_7_be.like.repository.LikeRepository;
import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.listup.repository.ListupRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final ListupRepository listupRepository;
    private final UserRepository userRepository;

    public boolean createLike(Long userId, Long listupId) {
        Optional<Listup> l = listupRepository.findById(listupId);
        Listup listup = l.get();
        Optional<User> u = userRepository.findById(userId);
        User user = u.get();

        Like existingLike = likeRepository.findByUserAndListup(user ,listup ).orElse(null);

        if (existingLike != null) {  // 이미 좋아요가 존재하면 취소
            likeRepository.delete(existingLike);
            return false;
        }
        else {
            Like like = Like.form(user, listup);
            likeRepository.save(like);
            return true;
        }
    }
}
