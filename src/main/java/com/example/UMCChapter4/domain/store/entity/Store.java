package com.example.UMCChapter4.domain.store.entity;

import com.example.UMCChapter4.domain.mission.entity.Mission;
import com.example.UMCChapter4.domain.review.entity.Review;
import com.example.UMCChapter4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "store")
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 15, nullable = false)
    private String name;

    @Column(name = "manager_number", nullable = false)
    private long managerNumber;

    @Column(name = "detail_address", length = 50, nullable = false)
    private String detailAddress;

    @OneToMany(mappedBy = "store") // store - review (1:N)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "store") // store - mission (1:N)
    private List<Mission> missionList;

    @ManyToOne(fetch = FetchType.LAZY) // store - location (N:1)
    @JoinColumn(name = "location_id", foreignKey = @ForeignKey(name = "fk_store_location_id"))
    private Location location;
}
