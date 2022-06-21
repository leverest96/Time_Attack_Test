package com.sparta.test0610.auth.controller;

import com.sparta.test0610.auth.dto.RequestLoginMemberDto;
import com.sparta.test0610.auth.service.AuthService;
import com.sparta.test0610.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse loginMember(HttpServletRequest request, HttpServletResponse response, @RequestBody RequestLoginMemberDto requestLoginMemberDto)
    {
        String token =  authService.login(request, response, requestLoginMemberDto);
        return ApiResponse.success("result",token);
    }

    @GetMapping("/refresh")
    public ApiResponse refreshMember(HttpServletRequest request, HttpServletResponse response)
    {
        return authService.refresh(request, response);
    }
}