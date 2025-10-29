package com.example.UMCChapter4.domain.member.entity.mapping;

import com.example.UMCChapter4.domain.member.entity.Food;
import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.member.entity.Term;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_term")
public class MemberTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // member_term - member (N:1)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_member_term_id"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY) // member_term - term (M:1)
    @JoinColumn(name = "term_id", foreignKey = @ForeignKey(name = "fk_term_member_id"))
    private Term term;
}
