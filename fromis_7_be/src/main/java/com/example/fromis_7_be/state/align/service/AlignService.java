package com.example.fromis_7_be.state.align.service;

import com.example.fromis_7_be.state.align.entity.Align;
import com.example.fromis_7_be.state.align.repository.AlignRepository;
import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.listup.repository.ListupRepository;
import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlignService {
    private final AlignRepository alignRepository;
    private final ListupRepository listupRepository;
    private final UserRepository userRepository;

    public boolean createAlign(Long userId, Long listupId) {
        Optional<Listup> l = listupRepository.findById(listupId);
        Listup listup = l.get();
        Optional<User> u = userRepository.findById(userId);
        User user = u.get();

        Align existingalgin = alignRepository.findByUserAndListup(user ,listup ).orElse(null);

        if (existingalgin != null) {  // 이미 좋아요가 존재하면 취소
            alignRepository.delete(existingalgin);
            return false;
        }
        else {
            Align align = Align.form(user, listup);
            alignRepository.save(align);
            return true;
        }
    }
}
