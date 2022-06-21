package com.sparta.test0610.member.repository;

import com.sparta.test0610.member.entity.Gender;
import com.sparta.test0610.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllByMyServiceAgeEqualsAndMyServiceGenderNot(int myServiceAge, Gender myServiceGender);
    Member findByMyServiceEmail(String email);
}