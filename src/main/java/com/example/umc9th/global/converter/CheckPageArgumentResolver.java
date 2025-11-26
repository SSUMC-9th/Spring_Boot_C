package com.example.umc9th.global.converter;

import com.example.umc9th.global.annotation.CheckPage;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CheckPageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CheckPage.class) &&
                (parameter.getParameterType() == Integer.class || parameter.getParameterType() == int.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String pageStr = webRequest.getParameter("page");

        if (pageStr == null) {
            return 0; // 0-based index
        }

        int page;
        try {
            page = Integer.parseInt(pageStr);
        } catch (NumberFormatException e) {
            throw new GeneralException(GeneralErrorCode.PAGE_NUMBER_INVALID);
        }

        if (page <= 0) {
            throw new GeneralException(GeneralErrorCode.PAGE_NUMBER_INVALID);
        }

        return page - 1;
    }
}
