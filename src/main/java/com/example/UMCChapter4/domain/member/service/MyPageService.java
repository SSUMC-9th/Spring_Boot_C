package com.example.UMCChapter4.domain.member.service;

import com.example.UMCChapter4.domain.member.dto.MyPageDto;
import com.example.UMCChapter4.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MemberRepository memberRepository;
    // 마이페이지 정보
    public MyPageDto getMyPage(Long memberId) {
        return memberRepository.findMyPageInfo(memberId)
                .orElseThrow(() -> new EntityNotFoundException("member not found"));
    }
}
