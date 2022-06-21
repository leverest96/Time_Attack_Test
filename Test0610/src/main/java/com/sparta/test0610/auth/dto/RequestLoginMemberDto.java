package com.sparta.test0610.auth.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestLoginMemberDto {
    private String email;

    @Builder
    public RequestLoginMemberDto(String email) {
        this.email = email;
    }
}