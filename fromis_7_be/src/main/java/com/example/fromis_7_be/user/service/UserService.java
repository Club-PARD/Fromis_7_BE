package com.example.fromis_7_be.user.service;

import com.example.fromis_7_be.user.dto.UserRequest;
import com.example.fromis_7_be.user.dto.UserResponse;
import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.user.repository.UserRepository;
import com.example.fromis_7_be.userpiece.dto.UserPieceResponse;
import com.example.fromis_7_be.util.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final S3Service s3Service;
    public UserResponse.ReadUser readUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NoSuchElementException("User not found with id: " + userId));

        List<UserPieceResponse.UserPieceReadResponse> userPieces = user.getUserPieces().stream()
                .map(userPiece -> UserPieceResponse.UserPieceReadResponse.from(userPiece.getPiece().getId(),userPiece.getUser().getId()))
                .collect(Collectors.toList());

        return UserResponse.ReadUser.from(user, userPieces);
    }
    public void createUser(UserRequest.UserCreateRequest req){
        LocalDateTime now = LocalDateTime.now();
        User u = new User(null, req.getName(), req.getImage(), now, now, new ArrayList<>());
        userRepository.save(u);
    }
    public UserResponse.ReadUser updateUser(Long userId, UserRequest.UserCreateRequest req){
        Optional<User> u = userRepository.findById(userId);
        User user = u.get();
        LocalDateTime now = LocalDateTime.now();
        user.update(req.getName(), req.getImage(), now);
        userRepository.save(user);
        return readUser(userId);
    }
    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        userRepository.delete(user);
    }
    public UserResponse.ImageRet updateImage(Long userId, MultipartFile image) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("findById userId 에 문제가 발생"));
        String imageUrl = s3Service.upload(image,
                "fromis7");
        user.setImage(imageUrl);
        userRepository.save(user);
        return UserResponse.ImageRet.builder()
                .imageUrl(imageUrl).build();
    }

}
