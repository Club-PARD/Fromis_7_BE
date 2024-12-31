package com.example.fromis_7_be.login.controller;

import com.example.fromis_7_be.login.service.SigininService;
import com.example.fromis_7_be.user.dto.UserRequest;
import com.example.fromis_7_be.user.dto.UserResponse;
import com.example.fromis_7_be.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("signin")
@RequiredArgsConstructor
public class SigninController {
    private final UserService userService;
    private final SigininService sigininService;

    @PostMapping("/access")
    @Operation(summary = "회원가입할 때 쓰세요")
    public ResponseEntity<String> access(@RequestBody UserRequest.UserCreateRequest req) {
        userService.createUser(req); // 사용자 생성 로직 호출
        return ResponseEntity.status(HttpStatus.OK)
                .body("회원가입 되었습니다.");
    }


    @PostMapping("/login")
    @Operation(summary = "로그인할 떄 쓰세요")
    public ResponseEntity<UserResponse.ReadUser> login(@RequestBody UserRequest.UserCreateRequest req) {
        UserResponse.ReadUser ret = sigininService.loginuser(req);

    }
}
