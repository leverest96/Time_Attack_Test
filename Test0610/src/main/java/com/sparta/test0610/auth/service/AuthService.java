package com.sparta.test0610.auth.service;

import com.sparta.test0610.auth.dto.RequestLoginMemberDto;
import com.sparta.test0610.common.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    String login(HttpServletRequest request, HttpServletResponse response, RequestLoginMemberDto requestLoginMemberDto);
    ApiResponse refresh(HttpServletRequest request, HttpServletResponse response);
}