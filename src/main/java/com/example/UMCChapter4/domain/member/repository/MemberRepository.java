package com.example.UMCChapter4.domain.member.repository;

import com.example.UMCChapter4.domain.member.dto.MyPageDto;
import com.example.UMCChapter4.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 마이페이지 정보
    @Query("""
    select new com.example.UMCChapter4.domain.member.dto.MyPageDto(
      m.email,
      m.phoneNumber,
      m.points
    )
    from Member m
    where m.id = :memberId
    """)
    Optional<MyPageDto> findMyPageInfo(@Param("memberId") Long userId);

    @Query("select m.points from Member m where m.id = :memberId")
    Integer findPointsByUserId(@Param("memberId") Long memberId);
}

