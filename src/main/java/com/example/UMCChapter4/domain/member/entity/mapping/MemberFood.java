package com.example.UMCChapter4.domain.member.entity.mapping;

import com.example.UMCChapter4.domain.member.entity.Food;
import com.example.UMCChapter4.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_food")
public class MemberFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // member_food - member (N:1)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_member_food_id"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY) // member_food - food (M:1)
    @JoinColumn(name = "food_id", foreignKey = @ForeignKey(name = "fk_food_member_id"))
    private Food food;
}
