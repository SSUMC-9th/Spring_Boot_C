package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.request.MemberRequestDto;
import com.example.umc9th.domain.member.dto.response.MemberResponseDto;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.auth.enums.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    @Transactional
    public MemberResponseDto.JoinDTO signup(
            MemberRequestDto.JoinDTO dto
    ){
        String salt = passwordEncoder.encode(dto.password());
        // 사용자 생성
        Member member = MemberConverter.toMember(dto, salt, ERole.ROLE_USER);
        // DB 적용
        memberRepository.save(member);
        // 응답 DTO 생성
        return MemberConverter.toJoinDTO(member);
    }
}
