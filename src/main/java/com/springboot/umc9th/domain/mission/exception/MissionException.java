package com.springboot.umc9th.domain.mission.exception;

import com.springboot.umc9th.global.apiPayload.code.BaseErrorCode;
import com.springboot.umc9th.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }}