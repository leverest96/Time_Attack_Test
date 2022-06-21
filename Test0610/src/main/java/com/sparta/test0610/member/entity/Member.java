package com.sparta.test0610.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.test0610.member.dto.RequestUpdateMemberDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Timestamped{
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column(nullable = false)
    private String myServiceEmail;

    @Column(nullable = false)
    private String myServiceName;

    @Column(nullable = false)
    private int myServiceAge;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender myServiceGender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType myServiceRoleType;

    @Column(nullable = false)
    private String myServiceRefreshToken;

    @Builder
    public Member(String email, String name, int age, Gender gender, RoleType roleType) {
        this.myServiceEmail = email;
        this.myServiceName = name;
        this.myServiceAge = age;
        this.myServiceGender = gender;
        this.myServiceRoleType = roleType;
    }

    public void updateRefreshToken(String refreshToken)
    {
        this.myServiceRefreshToken = refreshToken;
    }
    public void updateProfile(RequestUpdateMemberDto requestUpdateMemberDto) {
        this.myServiceName = requestUpdateMemberDto.getName();
        this.myServiceAge = requestUpdateMemberDto.getAge();
        this.myServiceGender =requestUpdateMemberDto.getGender() == 0 ? Gender.M : Gender.F;
    }
}