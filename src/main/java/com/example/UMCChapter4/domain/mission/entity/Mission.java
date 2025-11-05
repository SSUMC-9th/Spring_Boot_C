package com.example.UMCChapter4.domain.mission.entity;

import com.example.UMCChapter4.domain.store.entity.Store;
import com.example.UMCChapter4.global.entity.BaseEntity;
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
@Table
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "conditions", length = 100, nullable = false)
    private String condition;

    @Column(name = "point", nullable = false)
    private Integer points; // 0 초기화

    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE) // mission - member_mission (1:N)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY) // mission - store (N:1)
    @JoinColumn(name = "store_id", foreignKey = @ForeignKey(name = "fk_mission_store_id"))
    private Store store;

}
