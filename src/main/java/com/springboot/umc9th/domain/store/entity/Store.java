package com.springboot.umc9th.domain.store.entity;

import com.springboot.umc9th.domain.mission.entity.Mission;
import com.springboot.umc9th.domain.review.entity.Review;
import com.springboot.umc9th.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "Store")
public class Store extends BaseEntity {


    @Column(name = "store_name", nullable = false, length = 50)
    private String storeName;

    @Column(name = "store_address", nullable = false , length = 100)
    private String storeAddress;

    @Column(name = "store_number", nullable = false , length = 50)
    private String storeNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loacl")
    private Local local;

    @OneToMany(mappedBy = "store")
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store" , cascade = CascadeType.REMOVE)
    private List<Review> reviewList = new ArrayList<>();

}
