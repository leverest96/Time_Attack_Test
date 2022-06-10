package com.sparta.test0610;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 정보를 물고 다니기
@RequiredArgsConstructor // final용
public class UserRequestDto {
    private final String email;
    private final String name;
    private final int age;
    private final Long gender;
}