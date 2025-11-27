package com.springboot.umc9th.domain.mission.service;

import com.springboot.umc9th.domain.member.entity.Member;
import com.springboot.umc9th.domain.member.repository.MemberRepository;
import com.springboot.umc9th.domain.mission.converter.MissionConverter;
import com.springboot.umc9th.domain.mission.dto.res.MissionResDTO;
import com.springboot.umc9th.domain.mission.entity.Mission;
import com.springboot.umc9th.domain.mission.entity.mapping.UserMission;
import com.springboot.umc9th.domain.mission.repository.MissionRepository;
import com.springboot.umc9th.domain.mission.repository.UserMissionRepository;
import com.springboot.umc9th.domain.store.entity.Store;
import com.springboot.umc9th.domain.store.exception.StoreException;
import com.springboot.umc9th.domain.store.exception.code.StoreErrorCode;
import com.springboot.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository; // [추가]
    private final MemberRepository memberRepository;

    @Override
    public MissionResDTO.MissionPreViewListDTO getMissionList(Long storeId, Integer page) {

        //가게 확인 없으면 예외처리
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        //페이징 설정 (0부터 시작하므로 page - 1)
        PageRequest pageRequest = PageRequest.of(page - 1, 10);


        Page<Mission> missionPage = missionRepository.findAllByStore(store, pageRequest);

        return MissionConverter.toMissionPreViewListDTO(missionPage);
    }

    @Override
    public MissionResDTO.MyMissionPreViewListDTO getMyMissionList(Long memberId, Integer page) {

        //멤버 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));

        //페이징 설정
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        //진행 중인 미션 = complete가 false인 미션
        Page<UserMission> userMissionPage = userMissionRepository.findAllByMemberAndComplete(
                member,
                false, // false: 아직 완료하지 않음
                pageRequest
        );
        return MissionConverter.toMyMissionPreViewListDTO(userMissionPage);
    }
}
