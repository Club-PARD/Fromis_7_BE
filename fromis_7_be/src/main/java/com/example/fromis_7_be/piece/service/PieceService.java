package com.example.fromis_7_be.piece.service;

import com.example.fromis_7_be.piece.dto.PieceRequest;
import com.example.fromis_7_be.piece.dto.PieceResponse;
import com.example.fromis_7_be.piece.entity.Piece;
import com.example.fromis_7_be.piece.repository.PieceRepository;
import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.user.repository.UserRepository;
import com.example.fromis_7_be.userpiece.entity.UserPiece;
import com.example.fromis_7_be.userpiece.repository.UserPieceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PieceService {
    private final UserRepository userRepository;
    private final PieceRepository pieceRepository;
    private final UserPieceRepository userPieceRepository;

    public void createPieceByUserId(Long userId, PieceRequest.PieceCreateRequest req){
        User user =userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("찾으시는 User 정보: " + userId + "가 존재하지 않습니다."));
        Piece piece = Piece.from(req.getTitle(), req.getMemberNames(), req.getColor(), req.getStartYear(),
                req.getStartMonth(), req.getStartDay(), req.getEndYear(), req.getEndMonth(), req.getEndDay());
        pieceRepository.save(piece);
        UserPiece userPiece = UserPiece.builder()
                .user(user)
                .piece(piece)
                .build();

        userPieceRepository.save(userPiece);
    }

    public List<PieceResponse.PieceReadResponse> readPieceByUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("\"찾으시는 User 정보: \" + userId + \"가 존재하지 않습니다.\""));

        List<Piece> pieces = pieceRepository.findAllByUser(user);

        return pieces.stream()
                .map(PieceResponse.PieceReadResponse::from)
                .toList();
    }
    public PieceResponse.PieceReadResponse update(Long pieceId, PieceRequest.PieceCreateRequest req){
        Optional<Piece> b = pieceRepository.findById(pieceId);
        Piece piece = b.get();
        piece.update(req.getTitle(), req.getMemberNames(), req.getColor(), req.getStartYear(),
                req.getStartMonth(), req.getStartDay(), req.getEndYear(), req.getEndMonth(), req.getEndDay());
        pieceRepository.save(piece);

        return new PieceResponse.PieceReadResponse(piece.getId(), piece.getTitle(), piece.getMemberNames(),
                piece.getColor(), piece.getStartYear(), piece.getStartMonth(), piece.getStartDay(), piece.getEndYear(),
                piece.getEndMonth(), piece.getEndDay());
    }
    public void delete(Long pieceId){
        Piece piece = pieceRepository.findById(pieceId).orElseThrow(IllegalAccessError::new);
        pieceRepository.delete(piece);
    }
}
