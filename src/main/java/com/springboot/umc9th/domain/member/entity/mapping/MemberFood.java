package com.springboot.umc9th.domain.member.entity.mapping;

import com.springboot.umc9th.domain.member.entity.Food;
import com.springboot.umc9th.domain.member.entity.Member;
import com.springboot.umc9th.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "member_food")
public class MemberFood extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;
}
