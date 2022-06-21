package com.sparta.test0610.member.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseMemberDto {
    private String email;
    private String name;
    private int age;
    private int gender;

    @Builder
    public ResponseMemberDto(String email, String name, int age, int gender) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}