package com.example.fromis_7_be.listup.controller;

import com.example.fromis_7_be.listup.dto.ListupRequest;
import com.example.fromis_7_be.listup.dto.ListupResponse;
import com.example.fromis_7_be.listup.service.ListupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lists")
public class ListupController {
    private final ListupService listupService;

    @PostMapping("/{categoryId}")
    public void createListupByCate(@PathVariable Long cateId, @RequestBody ListupRequest.ListupCreateRequest req){
        listupService.createListupByCateId(cateId, req);
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
