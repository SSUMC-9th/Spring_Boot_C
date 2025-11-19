package com.springboot.umc9th.domain.member.exception;

import com.springboot.umc9th.global.apiPayload.code.BaseErrorCode;
import com.springboot.umc9th.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }
}
