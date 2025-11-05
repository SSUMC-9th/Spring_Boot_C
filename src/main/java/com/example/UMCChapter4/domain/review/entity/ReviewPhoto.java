package com.example.UMCChapter4.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table
public class ReviewPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "photo_url", length = 100, nullable = false)
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY) // review_photo - review (N:1)
    @JoinColumn(name = "review_id", foreignKey = @ForeignKey(name = "fk_photo_review_id"))
    private Review review;
}
