package com.example.fromis_7_be.listup.controller;

import com.example.fromis_7_be.category.dto.CategoryRequest;
import com.example.fromis_7_be.category.entity.Category;
import com.example.fromis_7_be.category.repository.CategoryRepository;
import com.example.fromis_7_be.listup.dto.ListupRequest;
import com.example.fromis_7_be.listup.dto.ListupResponse;
import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.listup.repository.ListupRepository;
import com.example.fromis_7_be.listup.service.ListupService;
import com.example.fromis_7_be.metadata.controller.MetaDataContoller;
import com.example.fromis_7_be.metadata.service.MetadataService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lists")
public class ListupController {
    private final ListupService listupService;
    private final CategoryRepository categoryRepository;
    private final ListupRepository listupRepository;
    @PostMapping("/{categoryId}")
    @Operation(summary = "list 생성, categoryid 참조")
    public ResponseEntity<List<ListupResponse.ListupReadResponse>> createListupByCate(
            @PathVariable Long cateId,
            @RequestBody List<ListupRequest.ListupCreateRequest> req) {

        // 1. 카테고리 조회
        Category category = categoryRepository.findById(cateId)
                .orElseThrow(() -> new NoSuchElementException("찾으시는 category 정보: " + cateId + "가 존재하지 않습니다."));

        // 2. 요청 데이터를 Listup 엔티티로 변환 및 저장
        List<Listup> listups = req.stream()
                .map(request -> Listup.from(null, request.getUrl(), null, request.getDescription(), category))
                .collect(Collectors.toList());
        listupRepository.saveAll(listups);

        // 3. 저장된 데이터 응답 DTO로 변환
        List<ListupResponse.ListupReadResponse> response = listups.stream()
                .map(ListupResponse.ListupReadResponse::from)
                .collect(Collectors.toList());

        // 4. 응답 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{listId}")
    @Operation(summary = "list 하나의 정보 가져오기, listid 참조")
    public ListupResponse.ListupReadResponse readListupById(@PathVariable Long listId) {
        return listupService.readListupById(listId);
    }

    @PatchMapping("/{listId}")
    @Operation(summary = "list description 수정하기, listId 참조")
    public ResponseEntity<ListupResponse.ListupReadResponse> updateListupDescription(
            @PathVariable Long listId,
            @RequestBody ListupRequest.ListupUpdateRequest req) {
        ListupResponse.ListupReadResponse response = listupService.updateListupById(listId, req);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{listId}")
    @Operation(summary = "list 삭제하기, listId 참조")
    public ResponseEntity<Void> delete(@PathVariable Long listId) {
        listupService.delete(listId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/{categoryId}")
    @Operation(summary = "list 전체 정보 가져오기 , categoryid 참조")
    public List<ListupResponse.ListupReadResponse> readListupByCate(
            @RequestParam(value = "categoryId", required = false) Long categoryId) {

        return listupService.readListupByCategory(categoryId);
    }



}
