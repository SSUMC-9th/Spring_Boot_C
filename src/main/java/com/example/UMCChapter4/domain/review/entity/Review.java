package com.example.UMCChapter4.domain.review.entity;

import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.store.entity.Store;
import com.example.UMCChapter4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", length = 200, nullable = false) // 리뷰 없이 사진이랑 별점만
    private String description;

    @Column(name = "rate", nullable = false)
    private BigDecimal rate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // review - member (N:1)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_review_member_id"), nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY) // review - store (N:1)
    @JoinColumn(name = "store_id", foreignKey = @ForeignKey(name = "fk_review_store_id"), nullable = false)
    private Store store;

    @OneToMany(mappedBy = "review") // review - review_photo (1:N)
    private List<ReviewPhoto> reviewPhotoList;

    @OneToMany(mappedBy = "review") // review - review_reply (1:N)
    @BatchSize(size = 10)
    private List<ReviewReply> reviewReplyList;




}
