package com.example.UMCChapter4.domain.mission.service.query;

import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.member.exception.MemberException;
import com.example.UMCChapter4.domain.member.exception.code.MemberErrorCode;
import com.example.UMCChapter4.domain.member.repository.MemberRepository;
import com.example.UMCChapter4.domain.mission.converter.MemberMissionConverter;
import com.example.UMCChapter4.domain.mission.dto.MyMissionStatusDto;
import com.example.UMCChapter4.domain.mission.dto.membermission.MemberMissionResDTO;
import com.example.UMCChapter4.domain.mission.entity.MemberMission;
import com.example.UMCChapter4.domain.mission.enums.EStatus;
import com.example.UMCChapter4.domain.mission.repository.MemberMissionRepository;
import com.example.UMCChapter4.global.validator.PageValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;


    // 사용자의 상태별 미션 목록 보기
    public MemberMissionResDTO.MemberMissionStatusListDTO getStatusMemberMission(
            String status,
            String memberName,
            Integer pageNumber
    ) {
        int PAGE_SIZE = 10;

        Member member =  memberRepository.findByName(memberName)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE);
        EStatus eStatus = EStatus.valueOf(status);
        Page<MemberMission> result = memberMissionRepository.findAllByMemberAndStatus(member, eStatus, pageRequest);

        PageValidator.validatePageRequest(result); // pageNumber 검사

        return MemberMissionConverter.toMemberMissionStatusListDto(result);
    }
}
