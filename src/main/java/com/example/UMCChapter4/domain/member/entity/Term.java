package com.example.UMCChapter4.domain.member.entity;

import com.example.UMCChapter4.domain.member.entity.mapping.MemberFood;
import com.example.UMCChapter4.domain.member.entity.mapping.MemberTerm;
import com.example.UMCChapter4.domain.member.enums.TermName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "term")
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private TermName name;

//    @OneToMany(mappedBy = "term") // term - member (1:N)
//    private List<MemberTerm> memberTermList = new ArrayList<>(); // 약관이 삭제될 일 없
}
