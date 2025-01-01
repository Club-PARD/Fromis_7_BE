package com.example.fromis_7_be.login.service;

import com.example.fromis_7_be.user.dto.UserRequest;
import com.example.fromis_7_be.user.dto.UserResponse;
import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SigininService {
    private final UserRepository userRepository;

    public Optional<UserResponse.ReadUser> loginUser(UserRequest.LoginUserRequest req) {
        Optional<User> userOptional = userRepository.findByEmail(req.getEmail());

        if (userOptional.isEmpty()) {
            return Optional.empty();
        }

        User u = userOptional.get();
        System.out.println(u.getName());

        if (u.getPassword().equals(req.getPassword())) {
            return Optional.of(UserResponse.ReadUser.from(u));
        } else {
            return Optional.empty();
        }
    }

    public Boolean loginCheck(String id) {
        Optional<User> userOptional = userRepository.findByEmail(id);
        if (userOptional.isEmpty()) {
            return true;
        }
        else return false;
    }

}
