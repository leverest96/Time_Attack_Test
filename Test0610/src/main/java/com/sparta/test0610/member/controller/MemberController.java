package com.sparta.test0610.member.controller;

import com.sparta.test0610.auth.config.MemberPrincipal;
import com.sparta.test0610.common.ApiResponse;
import com.sparta.test0610.member.dto.RequestCreateMemberDto;
import com.sparta.test0610.member.dto.RequestUpdateMemberDto;
import com.sparta.test0610.member.dto.ResponseMemberDto;
import com.sparta.test0610.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public ApiResponse createMember(@RequestBody RequestCreateMemberDto requestCoupleDto){
        long retId = memberService.saveMember(requestCoupleDto);
        return ApiResponse.success("result",retId);
    }

    @Secured("ROLE_USER")
    @GetMapping("/read/")
    public ApiResponse readMember(MemberPrincipal memberPrincipal){
        ResponseMemberDto responseMemberDto = memberService.findMember(memberPrincipal.getId());
        return ApiResponse.success("result",responseMemberDto);
    }

    @Secured("ROLE_USER")
    @PutMapping("/update/{id}")
    public ApiResponse updateMember(MemberPrincipal memberPrincipal, @RequestBody RequestUpdateMemberDto requestUpdateMemberDto){
        long retId = memberService.modifyMember(memberPrincipal.getId(), requestUpdateMemberDto);
        return ApiResponse.success("result",retId);
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/delete/")
    public ApiResponse deleteMember(MemberPrincipal memberPrincipal){
        long retId = memberService.removeMember(memberPrincipal.getId());
        return ApiResponse.success("result", retId);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/recommend/")
    public ApiResponse recommendMember(MemberPrincipal memberPrincipal){
        List<ResponseMemberDto> responseMemberDtoList = memberService.recommendMember(memberPrincipal.getId());
        return ApiResponse.success("result",responseMemberDtoList);
    }
}