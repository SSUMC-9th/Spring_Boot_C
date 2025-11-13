package com.example.umc9th.domain.test.controller;

import com.example.umc9th.domain.test.converter.TestConverter;
import com.example.umc9th.domain.test.dto.response.TestResponseDTO;
import com.example.umc9th.domain.test.exception.TestException;
import com.example.umc9th.domain.test.service.query.TestQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    private final TestQueryService testQueryService;

    @GetMapping("/test")
    public ApiResponse<TestResponseDTO.Testing> test() throws Exception{
        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;
       // throw new TestException(GeneralErrorCode.UNAUTHORIZED);

        return ApiResponse.onSuccess(
                code, TestConverter.toTestingDTO("This is Test!"));
    }
    // 예외 상황
    @GetMapping("/exception")
    public ApiResponse<TestResponseDTO.Exception> exception(@RequestParam Long flag){
        testQueryService.checkFlag(flag);

        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, TestConverter.toExceptionDTO("This is Test!"));
    }

    // slack test
    @GetMapping("/error500")
    public String triggerError() {
        throw new TestException(GeneralErrorCode.INTERNAL_SERVER_ERROR);
    }
}
