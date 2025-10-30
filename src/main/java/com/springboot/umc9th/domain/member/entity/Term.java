package com.springboot.umc9th.domain.member.entity;

import com.springboot.umc9th.domain.member.entity.mapping.MemberFood;
import com.springboot.umc9th.domain.member.entity.mapping.MemberTerm;
import com.springboot.umc9th.domain.member.enums.TermName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "Term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private TermName name;

    @OneToMany(mappedBy = "term")
    private List<MemberTerm> membertermList = new ArrayList<>();

}
