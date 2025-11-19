package com.example.UMCChapter4.domain.mission.service.query;

import com.example.UMCChapter4.domain.mission.dto.MyMissionStatusDto;
import com.example.UMCChapter4.domain.mission.enums.EStatus;
import com.example.UMCChapter4.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {
    private final MemberMissionRepository memberMissionRepository;

    //멤버의 진행중, 진행완료 미션 보기
    public Page<MyMissionStatusDto> getMyStatusMissions(Long userId, EStatus status, Pageable pageable) {
        return memberMissionRepository.findMyMissions(userId, LocalDate.now(), status, pageable);
    }
}
