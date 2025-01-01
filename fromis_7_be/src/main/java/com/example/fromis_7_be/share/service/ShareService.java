package com.example.fromis_7_be.share.service;

import com.example.fromis_7_be.piece.entity.Piece;
import com.example.fromis_7_be.piece.repository.PieceRepository;
import com.example.fromis_7_be.share.dto.ShareResponse;
import com.example.fromis_7_be.share.entity.Share;
import com.example.fromis_7_be.share.repository.ShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShareService {
    private final ShareRepository shareRepository;
    private final PieceRepository pieceRepository;

    public ShareResponse.ShareRead createShareURL(Long pieceId, String shareURL) {
        Optional<Piece> p = pieceRepository.findById(pieceId);
        Piece piece = p.orElseThrow(() -> new IllegalArgumentException("Piece not found with id: " + pieceId));

        Share share = new Share(null, shareURL, LocalDateTime.now(), piece);
        shareRepository.save(share);

        piece.updateShare(share); // Piece에 Share를 설정
        pieceRepository.save(piece); // 업데이트된 Piece 저장

        return ShareResponse.ShareRead.from(share);
    }


    public ShareResponse.ShareRead getShareURL(Long shareId) {
        Optional<Share> s = shareRepository.findById(shareId);
        Share share = s.get();

        ShareResponse.ShareRead shareRead = ShareResponse.ShareRead.from(share);

        return shareRead;
    }

    public void deleteShareURL(Long shareId) {
        Optional<Share> s = shareRepository.findById(shareId);
        Share share = s.get();
        shareRepository.delete(share);
    }
}
