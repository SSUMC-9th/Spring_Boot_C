package com.springboot.umc9th.domain.test.exception;


import com.springboot.umc9th.global.apiPayload.code.BaseErrorCode;
import com.springboot.umc9th.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}

