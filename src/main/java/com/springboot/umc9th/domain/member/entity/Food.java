package com.springboot.umc9th.domain.member.entity;

import com.springboot.umc9th.domain.member.entity.mapping.MemberFood;
import com.springboot.umc9th.domain.member.enums.FoodName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "Food")
public class Food {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodName name;

    @OneToMany(mappedBy = "food")
    @Builder.Default
    private List<MemberFood> memberFoodList = new ArrayList<>();
}



