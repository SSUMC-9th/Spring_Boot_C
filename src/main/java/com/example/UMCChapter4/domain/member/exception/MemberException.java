package com.example.UMCChapter4.domain.member.exception;

import com.example.UMCChapter4.global.apiPayload.code.BaseErrorCode;
import com.example.UMCChapter4.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }
}