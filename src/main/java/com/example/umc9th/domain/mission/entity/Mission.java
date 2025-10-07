package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "point", nullable = false)
    private Long point;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;
}
