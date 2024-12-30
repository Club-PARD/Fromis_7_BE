package com.example.fromis_7_be.unlike.service;

import com.example.fromis_7_be.like.entity.Like;
import com.example.fromis_7_be.like.repository.LikeRepository;
import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.listup.repository.ListupRepository;
import com.example.fromis_7_be.unlike.entity.Unlike;
import com.example.fromis_7_be.unlike.repository.UnlikeRepository;
import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnlikeService {
    private final UnlikeRepository unlikeRepository;
    private final ListupRepository listupRepository;
    private final UserRepository userRepository;

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
            Unlike unlike = Unlike.form(user, listup);
            unlikeRepository.save(unlike);
            return true;
        }
    }
}
