package com.example.fromis_7_be.state.unlike.service;

import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.listup.repository.ListupRepository;
import com.example.fromis_7_be.state.like.entity.Like;
import com.example.fromis_7_be.state.like.repository.LikeRepository;
import com.example.fromis_7_be.state.unlike.entity.Unlike;
import com.example.fromis_7_be.state.unlike.repository.UnlikeRepository;
import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnlikeService {
    private final UnlikeRepository unlikeRepository;
    private final ListupRepository listupRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public boolean createUnlike(Long userId, Long listupId) {
        Optional<Listup> l = listupRepository.findById(listupId);
        Listup listup = l.get();
        Optional<User> u = userRepository.findById(userId);
        User user = u.get();

        Unlike existingUnlike = unlikeRepository.findByUserAndListup(user ,listup ).orElse(null);

        if (existingUnlike != null) {  // 이미 좋아요가 존재하면 취소
            unlikeRepository.delete(existingUnlike);
            return false;
        }
        else {
            Like existingLike = likeRepository.findByUserAndListup(user, listup).orElse(null);
            if (existingLike != null) {
                likeRepository.delete(existingLike); // 기존 좋아요 삭제
            }

            Unlike unlike = Unlike.form(user, listup);
            unlikeRepository.save(unlike);
            return true;
        }
    }
}
