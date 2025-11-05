package com.example.UMCChapter4.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table
public class ReviewReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY) // review_reply - review (N:1)
    @JoinColumn(name = "review_id", foreignKey = @ForeignKey(name = "fk_reply_review_id"))
    private Review review;
}
