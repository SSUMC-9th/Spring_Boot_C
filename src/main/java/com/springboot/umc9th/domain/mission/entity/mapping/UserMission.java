package com.springboot.umc9th.domain.mission.entity.mapping;

import com.springboot.umc9th.domain.member.entity.Member;
import com.springboot.umc9th.domain.mission.entity.Mission;
import com.springboot.umc9th.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "User_Mission")
public class UserMission extends BaseEntity {

    @Column(name = "complete",nullable = false)
    @Builder.Default //기본은 실패
    private Boolean complete = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

}
