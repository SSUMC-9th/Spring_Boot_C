package com.example.UMCChapter4.domain.store.entity;

import com.example.UMCChapter4.domain.member.enums.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 7, nullable = false)
    private String name;

    @OneToMany(mappedBy = "location") // location - store (1:N)
    private List<Store> storeList;
}
