package com.example.fromis_7_be.metadata.controller;


import com.example.fromis_7_be.metadata.service.MetadataService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class MetaDataContoller {

    private final MetadataService metadataService;

    @PostMapping("/metadataByUrl")
    @Operation(summary = "Metadata 처리하기")
    public ResponseEntity<Map<String, Object>> getPageData(@RequestBody Map<String, String> request) {
        String url = request.get("url");

        // URL 유효성 검사
        if (url == null || !url.matches("^https?://.*$")) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "유효한 URL을 입력하세요.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        try {
            Map<String, Object> data = metadataService.fetchMetadata(url);
            return ResponseEntity.ok(data);
        } catch (IOException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "페이지 데이터를 가져오는데 실패했습니다.");
            errorResponse.put("message", e.getMessage());

            // 개발 환경 여부에 따라 스택 트레이스 포함
            if (!"production".equals(System.getenv("ENV"))) {
                errorResponse.put("stack", e.toString());
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
//        } catch (WebDriverException e) {
//            Map<String, Object> errorResponse = new HashMap<>();
//            errorResponse.put("error", "Web Driver Error.");
//            errorResponse.put("message", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//        }
    }
}
