package com.example.UMCChapter4.domain.member.service.command;

import com.example.UMCChapter4.domain.member.converter.MemberConverter;
import com.example.UMCChapter4.domain.member.dto.MemberReqDTO;
import com.example.UMCChapter4.domain.member.dto.MemberResDTO;
import com.example.UMCChapter4.domain.member.entity.Food;
import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.member.entity.mapping.MemberFood;
import com.example.UMCChapter4.domain.member.exception.FoodException;
import com.example.UMCChapter4.domain.member.exception.MemberException;
import com.example.UMCChapter4.domain.member.exception.code.FoodErrorCode;
import com.example.UMCChapter4.domain.member.exception.code.MemberErrorCode;
import com.example.UMCChapter4.domain.member.repository.FoodRepository;
import com.example.UMCChapter4.domain.member.repository.MemberFoodRepository;
import com.example.UMCChapter4.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;

    // 회원가입
    @Transactional
    public MemberResDTO.MemberJoinDTO signUp(
            MemberReqDTO.MemberJoinDTO dto
    ){
        if (memberRepository.findByName(dto.name()).isPresent())
            throw new MemberException(MemberErrorCode.DUPLICATED);

        // 사용자 생성
        Member member = MemberConverter.toMember(dto);
        // DB 적용
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인
        if (dto.preferCategory().size() > 1){
            List<MemberFood> memberFoodList = new ArrayList<>();

            // 선호 음식 ID별 조회
            for (Long id : dto.preferCategory()){

                // 음식 존재 여부 검증
                Food food = foodRepository.findById(id)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

                // MemberFood 엔티티 생성 (컨버터 사용해야 함)
                MemberFood memberFood = MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build();

                // 사용자 - 음식 (선호 음식) 추가
                memberFoodList.add(memberFood);
            }

            // 모든 선호 음식 추가: DB 적용
            memberFoodRepository.saveAll(memberFoodList);
        }


        // 응답 DTO 생성
        return MemberConverter.toJoinDTO(member);
    }
}
