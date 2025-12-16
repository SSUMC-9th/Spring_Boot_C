package com.example.UMCChapter4.domain.member.service.command;

import com.example.UMCChapter4.domain.member.exception.code.MemberSuccessCode;
import com.example.UMCChapter4.domain.member.userdetails.CustomUserDetails;
import com.example.UMCChapter4.domain.member.converter.MemberConverter;
import com.example.UMCChapter4.domain.member.dto.MemberReqDTO;
import com.example.UMCChapter4.domain.member.dto.MemberResDTO;
import com.example.UMCChapter4.domain.member.entity.Food;
import com.example.UMCChapter4.domain.member.entity.Member;
import com.example.UMCChapter4.domain.member.entity.mapping.MemberFood;
import com.example.UMCChapter4.domain.member.enums.Role;
import com.example.UMCChapter4.domain.member.exception.FoodException;
import com.example.UMCChapter4.domain.member.exception.MemberException;
import com.example.UMCChapter4.domain.member.exception.code.FoodErrorCode;
import com.example.UMCChapter4.domain.member.exception.code.MemberErrorCode;
import com.example.UMCChapter4.domain.member.repository.FoodRepository;
import com.example.UMCChapter4.domain.member.repository.MemberFoodRepository;
import com.example.UMCChapter4.domain.member.repository.MemberRepository;
import com.example.UMCChapter4.global.apiPayload.ApiResponse;
import com.example.UMCChapter4.global.apiPayload.code.GeneralErrorCode;
import com.example.UMCChapter4.global.auth.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;
    private final CookieClearingLogoutHandler cookieClearingLogoutHandler;


    // 회원가입
    @Transactional
    @Override
    public MemberResDTO.MemberJoinDTO signUp(
            MemberReqDTO.MemberSignUpDTO dto
    ){
        if (memberRepository.findByEmail(dto.email()).isPresent())
            throw new MemberException(MemberErrorCode.DUPLICATED);

        // 솔트된 비밀번호 생성
        String salt = encoder.encode(dto.password());

        // 사용자 생성: 유저 / 관리자는 따로 API 만들어서 관리
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);

        // DB 적용
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인
        if (dto.preferCategory().size() > 1){
            List<MemberFood> memberFoodList = new ArrayList<>();

            // 선호 음식 ID별 조회
            for (Long id : dto.preferCategory()){

                // 음식 존재 여부 검증
                Food food = foodRepository.findById(id)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

                // MemberFood 엔티티 생성 (컨버터 사용해야 함)
                MemberFood memberFood = MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build();

                // 사용자 - 음식 (선호 음식) 추가
                memberFoodList.add(memberFood);
            }

            // 모든 선호 음식 추가: DB 적용
            memberFoodRepository.saveAll(memberFoodList);
        }


        // 응답 DTO 생성
        return MemberConverter.toJoinDTO(member);
    }

    @Override
    public MemberResDTO.MemberLoginDTO login(
            MemberReqDTO.MemberLoginDTO dto,
            HttpServletRequest request,
            String type
    ) {

        if (type.equalsIgnoreCase("session")){
            return sessionLogin(dto, request);
        }

        else if (type.equalsIgnoreCase("token")){
            return tokenLogin(dto);
        }

        else {
            throw new MemberException(GeneralErrorCode.BAD_REQUEST);
        }
    }

    @Override
    public MemberResDTO.MemberLoginDTO tokenLogin(
            MemberReqDTO.@Valid MemberLoginDTO dto
    ) {

        // Member 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.INVALID)); // 로그인 실패는 INVALID

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return MemberConverter.toLoginDTO(member.getId(), accessToken);
    }

    @Override
    public MemberResDTO.MemberLoginDTO sessionLogin(
            MemberReqDTO.@Valid MemberLoginDTO dto,
            HttpServletRequest request
    ) {
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.INVALID)); // 로그인 실패는 INVALID

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID);
        }

        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 인증된 Authentication 객체 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities() // { ROLE_USER }
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        // JSESSIONID 와 세션 SecurityContext 매칭
        HttpSession session = request.getSession(true); // 로그인마다 새로운 JSESSIONID
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        System.out.println(session.getId());

        return MemberConverter.toLoginDTO(member.getId());
    }

    @Override
    public MemberResDTO.MemberLogoutDTO sessionLogout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            throw new MemberException(MemberErrorCode.BAD_REQUEST);
        }

        request.getSession().invalidate();

        cookieClearingLogoutHandler.logout(request, response, null);

        return MemberConverter.toLogoutDTO(session.getId());
    }
}
