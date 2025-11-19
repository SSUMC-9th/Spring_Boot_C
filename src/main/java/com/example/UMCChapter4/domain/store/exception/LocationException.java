package com.example.UMCChapter4.domain.store.exception;

import com.example.UMCChapter4.global.apiPayload.code.BaseErrorCode;
import com.example.UMCChapter4.global.apiPayload.exception.GeneralException;

public class LocationException extends GeneralException {
    public LocationException(BaseErrorCode code) {
        super(code);
    }
}
