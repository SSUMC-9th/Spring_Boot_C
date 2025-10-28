package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.EPreferenceName;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "preference")
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preference_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private EPreferenceName preferenceName;
}
