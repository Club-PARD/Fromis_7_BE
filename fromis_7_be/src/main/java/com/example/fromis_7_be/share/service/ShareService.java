package com.example.fromis_7_be.share.service;

import com.example.fromis_7_be.piece.entity.Piece;
import com.example.fromis_7_be.piece.repository.PieceRepository;
import com.example.fromis_7_be.piece.service.PieceService;
import com.example.fromis_7_be.share.dto.ShareRequest;
import com.example.fromis_7_be.share.dto.ShareResponse;
import com.example.fromis_7_be.share.entity.Share;
import com.example.fromis_7_be.share.repository.ShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShareService {
    private final ShareRepository shareRepository;
    private final PieceRepository pieceRepository;

    public void createShareURL(Long pieceId, String shareURL) {
        Optional<Piece> p = pieceRepository.findById(pieceId);
        Piece piece = p.get();

        Share share = share.form()

        shareRepository.save(share);
    }

//    public void createPost(Long userid, PostRequestDto.PostCreateRequest req){
//        Optional<User> u = userRepo.findById(userid);
//        User user = u.get();
//        Post post = Post.form(req.getTitle(), req.getContent(), user);
//        postRepo.save(post);
//    }

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
