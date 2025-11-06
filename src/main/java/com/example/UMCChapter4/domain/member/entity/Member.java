package com.example.UMCChapter4.domain.member.entity;

import com.example.UMCChapter4.domain.member.entity.mapping.MemberFood;
import com.example.UMCChapter4.domain.mission.entity.MemberMission;
import com.example.UMCChapter4.domain.member.entity.mapping.MemberTerm;
import com.example.UMCChapter4.domain.member.enums.Address;
import com.example.UMCChapter4.domain.member.enums.Gender;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.global.entity.BaseEntity;
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

    @Column(name = "name", length = 3, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Address address = Address.NONE;

    @Column(name = "detail_address", length = 50, nullable = false)
    private String detailAddress;

    @Column(name = "points", nullable = false) // 0 초기화
    private Integer points;

    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @Column(name = "phone_number", length = 13)
    private String phoneNumber;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE) // member - food (1:N)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE) // member - term (1:N)
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE) // member - mission (1:N)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member") // member - review (1:N) / 회원탈퇴를 하더라도 리뷰는 삭제되면 안됨
    private List<Review> reviewList = new ArrayList<>();
}
