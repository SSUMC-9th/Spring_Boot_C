package com.springboot.umc9th.domain.member.service.query;

import com.springboot.umc9th.domain.member.dto.req.MemberReqDTO;
import com.springboot.umc9th.domain.member.dto.res.MemberResDTO;
import jakarta.validation.Valid;

public interface MemberQueryService {
    MemberResDTO.LoginDTO login(
            MemberReqDTO.@Valid LoginDTO dto
    );
}
