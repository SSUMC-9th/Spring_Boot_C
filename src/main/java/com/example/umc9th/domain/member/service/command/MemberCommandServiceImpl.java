package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.food.exception.FoodException;
import com.example.umc9th.domain.food.exception.code.FoodErrorCode;
import com.example.umc9th.domain.food.repository.FoodRepository;
import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.request.MemberRequestDTO;
import com.example.umc9th.domain.member.dto.response.MemberResponseDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.repository.MemberFoodRepository;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;

    // 회원가입
    @Override
    @Transactional
    public MemberResponseDTO.JoinDTO signUp(MemberRequestDTO.JoinDTO dto) {

        // 사용자 생성
        Member member = MemberConverter.toMember(dto);
        // DB 적용
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인
        if (dto.preferCategory().size() > 1){
            // for문보다 stream 이 더 빠름(성능 향상)
            // 선호 음식 ID별 조회
            // 음식 존재 여부 검증
            // 사용자 - 음식 (선호 음식) 추가
            List<MemberFood> memberFoodList = dto.preferCategory().stream()
                    // MemberFood 엔티티 생성 (컨버터 사용)
                    .map(id -> MemberFood.builder()
                            .member(member)
                            .food(foodRepository.getReferenceById(id))
//                            .food(foodRepository.findById(id)
//                                    .orElseThrow(()->new FoodException(FoodErrorCode.NOT_FOUND)))
                            .build()
                    ).collect(Collectors.toList());

            // 모든 선호 음식 추가: DB 적용
            memberFoodRepository.saveAll(memberFoodList);
        }
        // 응답 DTO 사용
        return MemberConverter.toJoinDTO(member);
    }
}
