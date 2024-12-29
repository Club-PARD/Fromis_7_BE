package com.example.fromis_7_be.listup.service;

import com.example.fromis_7_be.category.entity.Category;
import com.example.fromis_7_be.category.repository.CategoryRepository;
import com.example.fromis_7_be.listup.dto.ListupRequest;
import com.example.fromis_7_be.listup.dto.ListupResponse;
import com.example.fromis_7_be.listup.entity.Listup;
import com.example.fromis_7_be.listup.repository.ListupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListupService {
     private final CategoryRepository categoryRepository;
     private final ListupRepository listupRepository;

    public void createListupByCateId(Long cateId, List<ListupRequest.ListupCreateRequest> reqList) {
        Category category = categoryRepository.findById(cateId)
                .orElseThrow(() -> new NoSuchElementException("찾으시는 category 정보: " + cateId + "가 존재하지 않습니다."));
        List<Listup> listups = reqList.stream()
                .map(req -> Listup.from(null, req.getUrl(), null, req.getDescription(), category))
                .collect(Collectors.toList());
        listupRepository.saveAll(listups);
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
                             listup.getDescription()
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
