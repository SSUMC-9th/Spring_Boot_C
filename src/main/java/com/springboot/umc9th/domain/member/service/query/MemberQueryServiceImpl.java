package com.springboot.umc9th.domain.member.service.query;

import com.springboot.umc9th.domain.member.converter.MemberConverter;
import com.springboot.umc9th.domain.member.dto.req.MemberReqDTO;
import com.springboot.umc9th.domain.member.dto.res.MemberResDTO;
import com.springboot.umc9th.domain.member.entity.Member;
import com.springboot.umc9th.domain.member.exception.MemberException;
import com.springboot.umc9th.domain.member.exception.code.MemberErrorCode;
import com.springboot.umc9th.domain.member.repository.MemberRepository;
import com.springboot.umc9th.domain.member.security.CustomUserDetails;
import com.springboot.umc9th.domain.member.security.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public MemberResDTO.LoginDTO login(
            MemberReqDTO.@Valid LoginDTO dto
    ) {

        // Member 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return MemberConverter.toLoginDTO(member, accessToken);
    }
}
