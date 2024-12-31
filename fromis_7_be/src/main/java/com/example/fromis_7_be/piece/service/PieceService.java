package com.example.fromis_7_be.piece.service;

import com.example.fromis_7_be.alarm.service.AlarmService;
import com.example.fromis_7_be.category.entity.Category;
import com.example.fromis_7_be.category.repository.CategoryRepository;
import com.example.fromis_7_be.piece.dto.PieceRequest;
import com.example.fromis_7_be.piece.dto.PieceResponse;
import com.example.fromis_7_be.piece.entity.Piece;
import com.example.fromis_7_be.piece.repository.PieceRepository;
import com.example.fromis_7_be.user.entity.User;
import com.example.fromis_7_be.user.repository.UserRepository;
import com.example.fromis_7_be.userpiece.entity.UserPiece;
import com.example.fromis_7_be.userpiece.repository.UserPieceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PieceService {
    private final UserRepository userRepository;
    private final PieceRepository pieceRepository;
    private final UserPieceRepository userPieceRepository;
    private final CategoryRepository categoryRepository;
    private final AlarmService alarmService;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createPieceByUserId(Long userId, PieceRequest.PieceCreateRequest req){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("찾으시는 User 정보: " + userId + "가 존재하지 않습니다."));
        Piece piece = Piece.from(req.getTitle(), req.getMemberNames(), req.getColor(), req.getStartYear(),
                req.getStartMonth(), req.getStartDay(), req.getEndYear(), req.getEndMonth(), req.getEndDay());
        pieceRepository.save(piece);

        UserPiece userPiece = UserPiece.builder()
                .user(user)
                .piece(piece)
                .build();

        userPieceRepository.save(userPiece);
        // 알림 생성 및 전송
        alarmService.notifyPieceCreated(user, piece);

    }

    public List<PieceResponse.PieceReadResponse> readPieceByUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("\"찾으시는 User 정보: \" + userId + \"가 존재하지 않습니다.\""));

        List<Piece> pieces = pieceRepository.findAllByUser(user);
        List<Category> highlightCategories = categoryRepository.findByHighlight(PageRequest.of(0, 4));

        return pieces.stream()
                .map(piece -> {
                    PieceResponse.PieceReadResponse response = PieceResponse.PieceReadResponse.from(piece);
                    response.setCategories(highlightCategories); // 하이라이트 카테고리 추가
                    return response;
                })
                .collect(Collectors.toList());
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public PieceResponse.PieceReadResponse update(Long pieceId, PieceRequest.PieceCreateRequest req){
        Piece piece = pieceRepository.findById(pieceId)
                .orElseThrow(() -> new NoSuchElementException("Piece ID " + pieceId + "를 찾을 수 없습니다."));

        piece.update(req.getTitle(), req.getMemberNames(), req.getColor(), req.getStartYear(),
                req.getStartMonth(), req.getStartDay(), req.getEndYear(), req.getEndMonth(), req.getEndDay());
        pieceRepository.save(piece);

        return new PieceResponse.PieceReadResponse(piece.getId(), piece.getTitle(), piece.getMemberNames(),
                piece.getColor(), piece.getStartYear(), piece.getStartMonth(), piece.getStartDay(), piece.getEndYear(),
                piece.getEndMonth(), piece.getEndDay(), piece.getCategories());
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Long pieceId){
        Piece piece = pieceRepository.findById(pieceId).orElseThrow(IllegalAccessError::new);
        pieceRepository.delete(piece);
    }
}
