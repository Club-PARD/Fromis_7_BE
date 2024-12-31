package com.example.fromis_7_be.share.controller;

import com.example.fromis_7_be.share.dto.ShareRequest;
import com.example.fromis_7_be.share.dto.ShareResponse;
import com.example.fromis_7_be.share.entity.Share;
import com.example.fromis_7_be.share.service.ShareService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.builder.ToStringSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/share")
public class ShareController {
    private ShareService shareService;

    @PostMapping("/{pieceId}")
    @Operation(summary = "shareid 만들기, pieceid 참조")
    public void createShareURL(@PathVariable Long pieceId) {

        String url = "https://fromis.stroe/piece" + pieceId;
        System.out.println(url);

        shareService.createShareURL(pieceId, url);

    }

    @GetMapping("/{shareId}")
    @Operation(summary = "shareid 불러오기, shareid 참조")
    public ShareResponse.ShareRead getShareURL(@PathVariable Long shareId) {
        return shareService.getShareURL(shareId);
    }

    @DeleteMapping("/{shareId}")
    @Operation(summary = "share")
    public void deleteShare(@PathVariable Long shareId) {
        shareService.deleteShareURL(shareId);
    }

}
