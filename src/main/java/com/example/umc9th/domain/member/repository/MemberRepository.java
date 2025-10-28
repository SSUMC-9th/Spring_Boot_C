package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.dto.MypageResponseDto;
import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("SELECT new com.example.umc9th.domain.member.dto.MypageResponseDto(m.id, m.name, m.email, m.phoneNum, m.point) FROM Member m WHERE m.id = :memberId")
    List<MypageResponseDto> findMypageInfoByMemberId(@Param("memberId") Long memberId);
}
