package com.example.fromis_7_be.user.controller;

import com.example.fromis_7_be.user.dto.UserRequest;
import com.example.fromis_7_be.user.dto.UserResponse;
import com.example.fromis_7_be.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    @Operation(summary = "user 하나의 정보 가져오기, userid 참조")
    public UserResponse.ReadUser getUser(@PathVariable Long userId){
        return userService.readUser(userId);
    }

    @PostMapping("")
    @Operation(summary = "user 추가하기")
    public void createUser(@RequestBody UserRequest.UserCreateRequest req){
        userService.createUser(req);
    }

    @PatchMapping("/{userId}/username")
    @Operation(summary = "user 이름 정보 수정, userid 참조")
    public UserResponse.ReadUser update(@PathVariable Long userId, @RequestBody UserRequest.UserUpdateRequest req){
        return userService.updateUser(userId, req);
    }
    @DeleteMapping("/{userId}")
    @Operation(summary = "user 정보 삭제, userid 참조")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @PatchMapping(value = "/{userId}/uploadImage", consumes = "multipart/form-data")
    @Operation(summary = "user 프로필 이미지 변경 api")
    public ResponseEntity<UserResponse.ImageRet> uploadImage(@PathVariable Long userId, @RequestPart MultipartFile image) throws IOException {
        UserResponse.ImageRet ret = userService.updateImage(userId, image);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

}