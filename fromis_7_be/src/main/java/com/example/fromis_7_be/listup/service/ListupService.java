package com.example.fromis_7_be.listup.service;

import com.example.fromis_7_be.category.entity.Category;
import com.example.fromis_7_be.category.repository.CategoryRepository;
import com.example.fromis_7_be.comment.dto.CommentResponse;
import com.example.fromis_7_be.listup.dto.ListupRequest;
import com.example.fromis_7_be.listup.dto.ListupResponse;
import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.listup.repository.ListupRepository;
import com.example.fromis_7_be.metadata.service.MetadataService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ListupService {
     private final CategoryRepository categoryRepository;
     private final ListupRepository listupRepository;
     private final MetadataService metadataService;


    @Transactional
    public void createListupByCateId(Long cateId, List<ListupRequest.ListupCreateRequest> reqList) {
        Category category = categoryRepository.findById(cateId)
                .orElseThrow(() -> new NoSuchElementException("찾으시는 category 정보: " + cateId + "가 존재하지 않습니다."));
        List<Listup> listups = reqList.stream()
                .map(request -> {
                    String url = request.getUrl();
                    String description = request.getDescription();

                    Map<String, Object> metadata;
                    try {
                        metadata = metadataService.fetchMetadata(url);
                    } catch (IOException e) {
                        metadata = new HashMap<>();
                        metadata.put("title", null);
                        metadata.put("thumbnailUrl", null);
                        metadata.put("description", null);
                    }

                    return Listup.from(
                            (String) metadata.get("title"),
                            url,
                            (String) metadata.get("thumbnailUrl"),
                            description,
                            category
                    );
                })
                .collect(Collectors.toList());

        // 3. 저장
        listupRepository.saveAll(listups);
    }

    @Transactional
    public void deleteListupByCateId(Long cateId) {
        Category category = categoryRepository.findById(cateId)
                .orElseThrow(() -> new NoSuchElementException("Category not found: " + cateId));

        List<Listup> listups = listupRepository.findAllByCategory(category);

        listupRepository.deleteAll(listups);
    }

    @Transactional
    public ListupResponse.ListupReadResponse updateListupById(Long listId, String description) {
        Listup listup = listupRepository.findById(listId)
                .orElseThrow(() -> new NoSuchElementException("Listup not found: " + listId));

        listup.update(description);
        listupRepository.save(listup);

        return ListupResponse.ListupReadResponse.from(listup);
    }

    public List<ListupResponse.ListupReadResponse> readListupByCategory(Long cateId) {
        // 1. 카테고리 조회
        Category cate = categoryRepository.findById(cateId)
                .orElseThrow(() -> new NoSuchElementException("찾으시는 category 정보: " + cateId + "가 존재하지 않습니다."));

        // 2. Listup 데이터를 가져와서 변환
        return listupRepository.findAllByCategory(cate).stream()
                .map(ListupResponse.ListupReadResponse::from) // 변환 로직을 response에서 호출
                .collect(Collectors.toList());
    }

    public ListupResponse.ListupReadResponse readListupById(Long listId) {
        return listupRepository.findById(listId)
                .map(ListupResponse.ListupReadResponse::from)
                .orElseThrow(() -> new RuntimeException("Listup not found"));
    }
    @Transactional
     public void delete(Long listupId){
         Listup listup = listupRepository.findById(listupId)
                 .orElseThrow(() -> new NoSuchElementException("찾으시는 list 정보: " + listupId + "가 존재하지 않습니다."));
         listupRepository.delete(listup);
     }
}
