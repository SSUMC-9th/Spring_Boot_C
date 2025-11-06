package com.example.umc9th.domain.store.entity;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

// member의 address가 region을 포함하는지 모호해서 실행을 위해 주석 처리함
//    @OneToMany(mappedBy = "region")
//    private List<Member> memberList = new ArrayList<>();

    @OneToMany(mappedBy = "region")
    private List<Store> storeList = new ArrayList<>();

    @Column(name = "region_name", length = 20, nullable = false)
    private String regionName;


}
