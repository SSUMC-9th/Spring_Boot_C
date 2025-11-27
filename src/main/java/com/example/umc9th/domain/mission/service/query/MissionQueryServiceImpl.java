package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.response.MemberMissionResponseDTO;
import com.example.umc9th.domain.mission.dto.response.MissionResponseDTO;
import com.example.umc9th.domain.mission.entity.Mission;
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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    public MissionResponseDTO.MissionPreViewListDTO findMission(String storeName, Integer page){
        // 가게를 가져온다(가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(()->new StoreException(StoreErrorCode.NOT_FOUND));

        // 가게에 맞는 미션 가져옴 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);

        // 결과를 응답 DTO로 변환
        return MissionConverter.toMissionPreViewListDTO(result);
    }

    // 내가 진행중인 미션 목록
    @Override
    public MemberMissionResponseDTO.MemberMissionPreViewListDTO findMyMission(boolean status, Integer page) {
        Long memberId=1L;
        // 사용자 가져오기
        Member member = memberRepository.findById(memberId).orElseThrow(()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<MemberMission>result=memberMissionRepository.findByMemberAndStatus(member, status, pageRequest);
        return MemberMissionConverter.toMissionPreViewListDTO(result);
    }

}
