package com.sparta.test0610.member.service;

import com.sparta.test0610.member.dto.RequestCreateMemberDto;
import com.sparta.test0610.member.dto.RequestUpdateMemberDto;
import com.sparta.test0610.member.dto.ResponseMemberDto;
import com.sparta.test0610.member.entity.Gender;
import com.sparta.test0610.member.entity.Member;
import com.sparta.test0610.member.entity.RoleType;
import com.sparta.test0610.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public long saveMember(RequestCreateMemberDto requestCreateMemberDto) {
        Member member = Member.builder()
                .email(requestCreateMemberDto.getEmail())
                .name(requestCreateMemberDto.getName())
                .age(requestCreateMemberDto.getAge())
                .gender(requestCreateMemberDto.getGender() == 0 ? Gender.M : Gender.F)
                .roleType(RoleType.MEMBER)
                .build();
        member.updateRefreshToken("Not yet login");
        return memberRepository.save(member).getIdx();
    }

    @Override
    public ResponseMemberDto findMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        return ResponseMemberDto.builder()
                .email(member.getMyServiceEmail())
                .name(member.getMyServiceName())
                .age(member.getMyServiceAge())
                .gender(member.getMyServiceGender() == Gender.M ? 0 : 1)
                .build();
    }

    @Override
    public long modifyMember(Long id, RequestUpdateMemberDto requestUpdateMemberDto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        member.updateProfile(requestUpdateMemberDto);
        return memberRepository.save(member).getIdx();
    }

    @Override
    public long removeMember(Long id) {
        memberRepository.deleteById(id);
        return id;
    }
    @Transactional
    @Override
    public List<ResponseMemberDto> recommendMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        List<Member> memberList = memberRepository.findAllByMyServiceAgeEqualsAndMyServiceGenderNot(member.getMyServiceAge(),member.getMyServiceGender());
        List<ResponseMemberDto> resultList = new LinkedList<ResponseMemberDto>();
        for(Member matchMember : memberList)
        {
            resultList.add(ResponseMemberDto.builder()
                    .email(matchMember.getMyServiceEmail())
                    .name(matchMember.getMyServiceName())
                    .age(matchMember.getMyServiceAge())
                    .gender(matchMember.getMyServiceGender() == Gender.M ? 0 : 1)
                    .build());
        }
        return resultList;
    }
}