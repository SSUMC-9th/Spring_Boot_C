package com.example.UMCChapter4.domain.mission.entity;

import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.mission.enums.EStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_mission")
public class MemberMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @ManyToOne(fetch = FetchType.LAZY) // member_mission - member (N:1)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_member_mission_id")) // 미션이 삭제되더라도 진행 완료 목록에 표시돼야, complete 조회
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY) // member_mission - mission (M:1)
    @JoinColumn(name = "mission_id", foreignKey = @ForeignKey(name = "fk_mission_member_id"))
    private Mission mission;

    // 상태 변경 메서드
    public MemberMission setStatus(EStatus status) {
        this.status = status;
        return this;
    }
}
