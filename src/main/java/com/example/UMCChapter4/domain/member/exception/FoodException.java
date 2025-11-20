package com.example.UMCChapter4.domain.member.exception;

import com.example.UMCChapter4.global.apiPayload.code.BaseErrorCode;
import com.example.UMCChapter4.global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {
    public FoodException(BaseErrorCode code) {
        super(code);
    }
}
