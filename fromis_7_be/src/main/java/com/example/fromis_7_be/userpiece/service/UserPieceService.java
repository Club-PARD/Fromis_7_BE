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


    public UserPieceResponse.UserPieceReadResponse createUserPiece(Long userId, Long pieceId, UserPieceRequest.CreateUserPieceRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Piece piece = pieceRepository.findById(pieceId)
                .orElseThrow(() -> new IllegalArgumentException("Piece not found: " + pieceId));

        UserPiece userPiece = new UserPiece();
        userPiece.setUser(user);
        userPiece.setPiece(piece);
        userPieceRepository.save(userPiece);

        return UserPieceResponse.UserPieceReadResponse.from(user.getId(), piece.getId());
    }

    public UserPieceResponse.UserPieceReadResponse getUserPiece(Long userPieceId) {
        UserPiece userPiece = userPieceRepository.findById(userPieceId)
                .orElseThrow(() -> new IllegalArgumentException("UserPiece not found: " + userPieceId));

        return UserPieceResponse.UserPieceReadResponse.from(
                userPiece.getUser().getId(),
                userPiece.getPiece().getId()
        );
    }
    public void deleteUserPiece(Long userPieceId) {
        UserPiece userPiece = userPieceRepository.findById(userPieceId)
                .orElseThrow(() -> new IllegalArgumentException("UserPiece not found: " + userPieceId));
        userPieceRepository.delete(userPiece);
    }
}
