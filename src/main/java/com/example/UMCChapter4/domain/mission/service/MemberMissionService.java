package com.example.UMCChapter4.domain.mission.service;

import com.example.UMCChapter4.domain.mission.dto.MyMissionStatusDto;
import com.example.UMCChapter4.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MemberMissionService {
    private final MemberMissionRepository memberMissionRepository;

    //멤버의 진행중, 진행완료 미션 보기
    public Page<MyMissionStatusDto> getMyStatusMissions(Long userId, boolean complete, Pageable pageable) {
        return memberMissionRepository.findMyMissions(userId, LocalDate.now(), complete, pageable);
    }
}
