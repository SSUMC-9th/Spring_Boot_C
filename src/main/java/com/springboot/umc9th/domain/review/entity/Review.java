package com.springboot.umc9th.domain.review.entity;

import com.springboot.umc9th.domain.member.entity.Member;
import com.springboot.umc9th.domain.store.entity.Store;
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
@Table(name = "Review")
public class Review extends BaseEntity {

    @Column(name = "review_content",nullable = false,length = 100)
    private String reviewContent;

    @Column(name = "review_score")
    @Builder.Default //기본 0점
    private Integer reviewScore=0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "review" , cascade = CascadeType.REMOVE)
    private List<ReviewComment> reviewComments = new ArrayList<>();

    @OneToMany(mappedBy = "review",cascade = CascadeType.REMOVE)
    private List<ReviewImage> reviewImages = new ArrayList<>();



}
