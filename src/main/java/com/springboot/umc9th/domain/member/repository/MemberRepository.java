package com.springboot.umc9th.domain.member.repository;

import com.springboot.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
        /* findById도 마찬가지로 기본메서드에 포함되어 있음
            따로 메서드를 통해 작성하지 않아도 됨
         */



}
