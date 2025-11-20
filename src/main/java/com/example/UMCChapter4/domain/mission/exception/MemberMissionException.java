package com.example.UMCChapter4.domain.mission.exception;

import com.example.UMCChapter4.global.apiPayload.code.BaseErrorCode;
import com.example.UMCChapter4.global.apiPayload.exception.GeneralException;

public class MemberMissionException extends GeneralException {
    public MemberMissionException(BaseErrorCode code) {
        super(code);
    }
}

