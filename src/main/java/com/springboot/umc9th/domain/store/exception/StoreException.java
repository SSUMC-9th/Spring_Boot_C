package com.springboot.umc9th.domain.store.exception;

import com.springboot.umc9th.global.apiPayload.code.BaseErrorCode;
import com.springboot.umc9th.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
