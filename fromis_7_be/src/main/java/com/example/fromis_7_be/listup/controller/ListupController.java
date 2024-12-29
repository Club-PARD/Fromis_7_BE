package com.example.fromis_7_be.listup.controller;

import com.example.fromis_7_be.category.dto.CategoryRequest;
import com.example.fromis_7_be.category.entity.Category;
import com.example.fromis_7_be.category.repository.CategoryRepository;
import com.example.fromis_7_be.listup.dto.ListupRequest;
import com.example.fromis_7_be.listup.dto.ListupResponse;
import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.listup.repository.ListupRepository;
import com.example.fromis_7_be.listup.service.ListupService;
import lombok.RequiredArgsConstructor;
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
    public void createListupByCate(@PathVariable Long cateId, @RequestBody List<ListupRequest.ListupCreateRequest>  req){
        // 1. Category 확인
        Category category = categoryRepository.findById(cateId)
                .orElseThrow(() -> new NoSuchElementException("찾으시는 category 정보: " + cateId + "가 존재하지 않습니다."));

        // 2. Listup 엔티티 생성 및 저장
        List<Listup> listups = req.stream()
                .map(request -> Listup.from(null, request.getUrl(), null, request.getDescription(), category))
                .collect(Collectors.toList());

        listupRepository.saveAll(listups);
    }

    @GetMapping("/{listId}")
    public ListupResponse.ListupReadResponse readListupById(@PathVariable Long listId) {
        return listupService.readListupById(listId);
    }

    @GetMapping("")
    public List<ListupResponse.ListupReadResponse> readListupByCate(
            @RequestParam(value = "categoryId", required = false) Long categoryId) {
        return listupService.readListupByCategory(categoryId);
    }

    @PatchMapping("/{listId}")
    public ListupResponse.ListupReadResponse updateListup(
            @PathVariable Long listId,
            @RequestBody ListupRequest.ListupUpdateRequest req) {
        return listupService.updateListup(listId, req);
    }

    @DeleteMapping("/{listId}")
    public void deleteListup(@PathVariable Long listId) {
        listupService.delete(listId);
    }

}
