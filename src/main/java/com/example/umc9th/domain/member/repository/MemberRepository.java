package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.dto.response.MyPageResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT new com.example.umc9th.domain.member.dto.response.MyPageResponseDTO(m.id, m.name, m.email, m.phone, m.point) " +
            "FROM Member m WHERE m.id = :memberId")
    List<MyPageResponseDTO> findByMemberId(Long memberId);
}
