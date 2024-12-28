package com.example.fromis_7_be.userpiece.service;

import com.example.fromis_7_be.piece.entity.Piece;
import com.example.fromis_7_be.piece.repository.PieceRepository;
import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.user.repository.UserRepository;
import com.example.fromis_7_be.userpiece.dto.UserPieceRequest;
import com.example.fromis_7_be.userpiece.dto.UserPieceResponse;
import com.example.fromis_7_be.userpiece.entity.UserPiece;
import com.example.fromis_7_be.userpiece.repository.UserPieceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPieceService {
    private final UserPieceRepository userPieceRepository;
    private final UserRepository userRepository;
    private final PieceRepository pieceRepository;

    // 유저와 피스를 연결하는 메소드
    public UserPieceResponse.UserPieceReadResponse createUserPiece(UserPieceRequest.CreateUserPieceRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Piece piece = pieceRepository.findById(request.getPieceId())
                .orElseThrow(() -> new IllegalArgumentException("Piece not found"));

        UserPiece userPiece = new UserPiece();
        userPiece.setUser(user);
        userPiece.setPiece(piece);

        // UserPiece 저장
        userPieceRepository.save(userPiece);

        return UserPieceResponse.UserPieceReadResponse.from(user.getId(), piece.getId());
    }
}
