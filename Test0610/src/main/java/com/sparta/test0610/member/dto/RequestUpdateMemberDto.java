package com.sparta.test0610.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestUpdateMemberDto {
    private String name;
    private int age;
    private int gender;

    @Builder
    public RequestUpdateMemberDto(String name, int age, int gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}