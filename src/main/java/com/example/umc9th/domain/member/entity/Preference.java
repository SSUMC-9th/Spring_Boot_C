package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.entity.mapping.MemberPreference;
import com.example.umc9th.domain.member.enums.PreferenceName;

import com.example.umc9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "preference", fetch = FetchType.LAZY)
    private List<MemberPreference> memberPreferenceList = new ArrayList<>();


    @Column(name = "preference_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private PreferenceName preferenceName;
}
