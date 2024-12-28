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
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public UserResponse.ReadUser getUser(@PathVariable Long userId){
        return userService.readUser(userId);
    }
    @PostMapping("")
    public void createUser(@RequestBody UserRequest.UserCreateRequest req){
        userService.createUser(req);
    }

    @PatchMapping("/{userId}")
    public UserResponse.ReadUser update(@PathVariable Long userId, @RequestBody UserRequest.UserCreateRequest req){
        return userService.updateUser(userId, req);
    }
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @PostMapping(value = "/{id}/uploadImage", consumes = "multipart/form-data")
    @Operation(summary = "프로필 이미지 변경 api")
    public ResponseEntity<UserResponse.ImageRet> uploadImage(@PathVariable Long id, @RequestPart MultipartFile image) throws IOException {
        UserResponse.ImageRet ret = userService.updateImage(id, image);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

}