package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.entity.mapping.MemberTerm;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    // 초기값 지정 null 이 아닌 기본값 지정하기 위함
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "point")
    private int point;

    @Column(name = "inactive_at")
    private LocalDateTime inactiveAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default //  -> Builder 사용 시에도 기본값이 유지
    // 멤버가 지워지면 연관 테이블 데이터도 지워짐
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE,  orphanRemoval = true) // 내가 작성한 리뷰 조회
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();
}
