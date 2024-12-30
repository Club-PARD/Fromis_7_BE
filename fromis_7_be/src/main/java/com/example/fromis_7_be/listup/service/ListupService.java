package com.example.fromis_7_be.listup.service;

import com.example.fromis_7_be.category.entity.Category;
import com.example.fromis_7_be.category.repository.CategoryRepository;
import com.example.fromis_7_be.listup.dto.ListupRequest;
import com.example.fromis_7_be.listup.dto.ListupResponse;
import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.listup.repository.ListupRepository;
import com.example.fromis_7_be.metadata.service.MetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListupService {
     private final CategoryRepository categoryRepository;
     private final ListupRepository listupRepository;
     private final MetadataService metadataService;


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

    public void deleteListupByCateId(Long cateId) {
        Category category = categoryRepository.findById(cateId)
                .orElseThrow(() -> new NoSuchElementException("Category not found: " + cateId));

        List<Listup> listups = listupRepository.findAllByCategory(category);

        listupRepository.deleteAll(listups);
    }
     public List<ListupResponse.ListupReadResponse> readListupByCategory(Long cateId){
         Category cate = categoryRepository.findById(cateId)
                 .orElseThrow(() -> new NoSuchElementException("찾으시는 category 정보: " + cateId + "가 존재하지 않습니다."));
         return listupRepository.findAllByCategory(cate).stream()
                 .map(listup -> {
                     return new ListupResponse.ListupReadResponse(
                             listup.getListId(),
                             listup.getUrl(),
                             listup.getName(),
                             listup.getImage(),
                             listup.getDescription(),
                             listup.getLikes() == null ? 0 : listup.getLikes().size()// 좋아요 수 계산

                     );
                 })
                 .collect(Collectors.toList());
     }

    public ListupResponse.ListupReadResponse readListupById(Long listId) {
        return listupRepository.findById(listId)
                .map(ListupResponse.ListupReadResponse::from)
                .orElseThrow(() -> new RuntimeException("Listup not found"));
    }

    public ListupResponse.ListupReadResponse updateListup(Long listId, ListupRequest.ListupUpdateRequest req) {
        Listup listup = listupRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Listup not found"));

        listup.update(req.getName(), req.getUrl(), req.getImage(), req.getDescription());
        listupRepository.save(listup);

        return ListupResponse.ListupReadResponse.from(listup);
    }
     public void delete(Long listupId){
         Listup listup = listupRepository.findById(listupId)
                 .orElseThrow(() -> new NoSuchElementException("찾으시는 list 정보: " + listupId + "가 존재하지 않습니다."));
         listupRepository.delete(listup);
     }
}
