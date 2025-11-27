package com.example.umc9th.global.config;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.enums.ECategory;
import com.example.umc9th.domain.store.repository.RegionRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Transactional
public class DataInitializer implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final ReviewRepository reviewRepository;
    private final RegionRepository regionRepository;

    @Override
    public void run(String... args) throws Exception {

        // 데이터 중복 삽입 방지 (ID 1L이 이미 존재하면 건너뛰기)
        if (memberRepository.findById(1L).isEmpty()) {


            // 멤버 데이터 삽입 (Member ID: 1L)
            Member member1 = Member.builder()
                    .name("테스트 회원")
                    .birthDate(LocalDate.ofEpochDay(2025-12-2))
                    .point(1000L)
                    .address("서울 어딘가")
                    .email("")

                    .build();
            memberRepository.save(member1);

            // Region 데이터 삽입 (ID: 1L)
            Region regionSeoul = Region.builder()
                    .regionName("서울")
                    .build();
            regionRepository.save(regionSeoul);


            // 가게 데이터 삽입 (Store ID: 1L)
            Store storeA = Store.builder()
                    .name("테스트 가게")
                    .address("서울시 어딘가")
                    .ratingAverage(4.5f)
                    .category(ECategory.아시아)
                    .region(regionSeoul)
                    .build();
            storeRepository.save(storeA);


            // 미션 데이터 삽입 (Mission ID: 1L)
            // 가게 A에 종속된 미션
            Mission mission1 = Mission.builder()
                    .store(storeA) // 위에 만든 가게 A 객체 연결 (외래키)
                    .point(500L)
                    .content("메뉴 1개 이상 주문 후 리뷰 작성")
                    .dueDate(LocalDateTime.now().plusDays(7))
                    .build();
            missionRepository.save(mission1);


            // 리뷰 데이터 삽입 (ID: 1L)
            // Member 1L이 Store 1L에 작성한 리뷰
            Review review1 = Review.builder()
                    .member(member1) // 위에서 저장한 Member 객체 연결
                    .store(storeA)  // 위에서 저장한 Store 객체 연결
                    .ratingScore(5.0f)     // 평점
                    .content("음식이 매우 맛있고 분위기가 좋습니다. 미션 성공!")
                    .status(true)
                    .build();
            reviewRepository.save(review1);
            System.out.println("초기 테스트 데이터 로딩 완료: Member(1L), Store(1L), Mission(1L) 생성");
        } else {
            System.out.println("초기 데이터가 이미 존재합니다. 삽입을 건너뜁니다.");
        }



    }
}