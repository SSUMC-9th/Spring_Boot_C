package com.springboot.umc9th.domain.mission.entity;

import com.springboot.umc9th.domain.mission.entity.mapping.UserMission;
import com.springboot.umc9th.domain.store.entity.Store;
import com.springboot.umc9th.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "Mission")
public class Mission extends BaseEntity {

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "deadline",nullable = false)
    private LocalDateTime deadline;

    @Column(name = "mission_condition",nullable = false,length = 100)
    private String mission_condition;


    //관계 설정
    @OneToMany(mappedBy = "mission")
    private List<UserMission> userMissionList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

}
