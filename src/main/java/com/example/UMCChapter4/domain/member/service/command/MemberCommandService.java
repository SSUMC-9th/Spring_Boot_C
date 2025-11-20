package com.example.UMCChapter4.domain.member.service.command;

import com.example.UMCChapter4.domain.member.dto.MemberReqDTO;
import com.example.UMCChapter4.domain.member.dto.MemberResDTO;

public interface MemberCommandService {
    //회원가입
    MemberResDTO.MemberJoinDTO signUp(
            MemberReqDTO.MemberJoinDTO dto
    );
}
