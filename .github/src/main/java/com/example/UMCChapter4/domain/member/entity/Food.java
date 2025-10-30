package com.example.UMCChapter4.domain.member.entity;

import com.example.UMCChapter4.domain.member.entity.mapping.MemberFood;
import com.example.UMCChapter4.domain.member.enums.FoodName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private FoodName name = FoodName.NONE;

    @OneToMany(mappedBy = "food", cascade = CascadeType.REMOVE) // food - member (선호음식)
    private List<MemberFood> memberFoodList = new ArrayList<>();
}
