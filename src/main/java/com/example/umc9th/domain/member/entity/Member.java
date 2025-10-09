package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.entity.mapping.MemberPreference;
import com.example.umc9th.domain.member.entity.mapping.MemberTerm;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.global.entity.BaseEntity;
import com.example.umc9th.domain.member.enums.*;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //연관 관계
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberPreference> memberPreferenceList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Review> reviewList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private MemberMission memberMissionList;



    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "address", length = 20, nullable = false)
    private String address;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "point", nullable = false)
    private Long point;

    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @Column(name = "phone_num", length = 15, nullable = true)
    @Builder.Default
    private String phoneNum = null;

}
