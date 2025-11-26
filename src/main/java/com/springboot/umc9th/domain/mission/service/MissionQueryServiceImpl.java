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

        // 1. 가게 확인 (없으면 에러)
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다.")); // 커스텀 예외로 변경 권장

        // 2. 페이징 설정 (0부터 시작하므로 page - 1)
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        // 3. DB 조회
        Page<Mission> missionPage = missionRepository.findAllByStore(store, pageRequest);

        // 4. DTO 변환 후 리턴
        return MissionConverter.toMissionPreViewListDTO(missionPage);
    }

    @Override
    public MissionResDTO.MyMissionPreViewListDTO getMyMissionList(Long memberId, Integer page) {

        // 1. 멤버 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));

        // 2. 페이징 설정 (0부터 시작하므로 -1)
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        // 3. [수정됨] 진행 중인 미션 = complete가 false인 미션
        Page<UserMission> userMissionPage = userMissionRepository.findAllByMemberAndComplete(
                member,
                false, // false: 아직 완료하지 않음 (진행 중)
                pageRequest
        );

        // 4. 변환 (Converter는 아까 만든 것 그대로 쓰면 됩니다)
        return MissionConverter.toMyMissionPreViewListDTO(userMissionPage);
    }
}
