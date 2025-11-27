package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.enums.EStatus;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.exception.code.StoreException;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;


    // 가게별 미션 목록 조회 서비스
    @Override
    public MissionResponseDto.MissionPreViewListDTO getMissionListByStore(
            Long storeId,
            Integer page
    ) {
        // 가게 유효성 검사 (ID로만 확인하거나 Entity 조회)
        Store store = storeRepository.findById(storeId)
                // 없으면 예외 발생
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 페이지네이션 구현 (기본 사이즈 = 10)
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> missionPage = missionRepository.findAllByStoreId(storeId, pageRequest);

        return MissionConverter.toMissionPreViewListDTO(missionPage);
    }

    @Override
    public MissionResponseDto.MissionPreViewListDTO getMyMissionList(Long memberId, Integer page) {

        // 멤버 유효성 검증
        Member member = memberRepository.findById(memberId)
                // 없으면 예외 발생
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 페이지네이션 구현 (기본 사이즈 = 10)
        Pageable pageable = PageRequest.of(page, 10);

        // 진행 중인 미션 목록 조회 (status = IN_PROGRESS)
        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllByMemberAndStatus(member, EStatus.IN_PROGRESS, pageable);

        return MissionConverter.toMissionPreViewListDTOFromMemberMission(memberMissionPage);
    }
}
