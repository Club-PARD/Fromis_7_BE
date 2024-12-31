package com.example.fromis_7_be.login.service;

import com.example.fromis_7_be.user.dto.UserRequest;
import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SigininService {
    private final UserRepository userRepository;

    public Long loginuser(UserRequest.UserCreateRequest req) {

        Optional<User> user = userRepository.findByEmail(req.getEmail());
        System.out.println(user.isPresent());
        Long ret = 0L;
        //password 넣고 비교 해서 맞으면 user 객체 반환
        //아니면 user password 아니라고 알려주고
        //아니면 uer 없다는 거 알려주기 
        return ret;
    }
}
