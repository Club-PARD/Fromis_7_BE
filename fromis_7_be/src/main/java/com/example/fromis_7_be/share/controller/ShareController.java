package com.example.fromis_7_be.share.controller;


import com.example.fromis_7_be.share.dto.ShareResponse;
import com.example.fromis_7_be.share.service.ShareService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/share")
@RequiredArgsConstructor
public class ShareController {
    private final ShareService shareService ;

    @PostMapping("/{pieceId}")
    @Operation(summary = "shareid 만들기, pieceid 참조")
    public void createShareURL(@PathVariable Long pieceId) {

        String url = "https://fromis7.stroe/" + pieceId.toString();

        shareService.createShareURL(pieceId, url);

    }

    @GetMapping("/{shareId}")
    @Operation(summary = "shareid 불러오기, shareid 참조")
    public ShareResponse.ShareRead getShareURL(@PathVariable Long shareId) {
        return shareService.getShareURL(shareId);
    }

    @DeleteMapping("/{shareId}")
    @Operation(summary = "share 삭제하기(사용되지 않습니다.)")
    public void deleteShare(@PathVariable Long shareId) {
        shareService.deleteShareURL(shareId);
    }

}
