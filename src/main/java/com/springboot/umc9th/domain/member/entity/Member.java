package com.springboot.umc9th.domain.member.entity;


import com.springboot.umc9th.domain.member.entity.mapping.MemberFood;
import com.springboot.umc9th.domain.member.entity.mapping.MemberTerm;
import com.springboot.umc9th.domain.member.enums.Gender;
import com.springboot.umc9th.domain.mission.entity.mapping.UserMission;
import com.springboot.umc9th.domain.review.entity.Review;
import com.springboot.umc9th.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseEntity {

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name ="birthday")
    private LocalDate birthday;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "point")
    @Builder.Default //기본 포인트는 0으로 고정
    private Integer point = 0;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "inactive_data")
    private LocalDateTime inactiveData;

    //관계 설정

    @OneToMany(mappedBy = "member", cascade =  CascadeType.REMOVE)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade =  CascadeType.REMOVE)
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade =  CascadeType.REMOVE)
    private List<UserMission> userMissionList = new ArrayList<>();

}
