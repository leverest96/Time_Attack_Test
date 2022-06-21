package com.sparta.test0610.member.service;

import com.sparta.test0610.member.dto.RequestCreateMemberDto;
import com.sparta.test0610.member.dto.RequestUpdateMemberDto;
import com.sparta.test0610.member.dto.ResponseMemberDto;

import java.util.List;

public interface MemberService {
    long saveMember(RequestCreateMemberDto requestCreateMemberDto);
    ResponseMemberDto findMember(Long id);
    long removeMember(Long id);
    long modifyMember(Long id, RequestUpdateMemberDto requestUpdateMemberDto);
    List<ResponseMemberDto> recommendMember(Long id);
}