package com.example.UMCChapter4.global.validator;

import com.example.UMCChapter4.global.annotation.ValidPage;
import com.example.UMCChapter4.global.apiPayload.code.PageErrorCode;
import com.example.UMCChapter4.global.apiPayload.exception.GeneralException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer pageNumber, ConstraintValidatorContext context) {
        if (pageNumber < 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    PageErrorCode.INVALID_PAGE_NUMBER.getMessage()).addConstraintViolation();

            return false;
        }
        return true;
    }

    public static <T> void validatePageRequest(Page<T> result){
        if (result.getNumber() > result.getTotalPages()) {
            throw new GeneralException(PageErrorCode.INVALID_PAGE_NUMBER);
        }
    }

    @Override
    public void initialize(ValidPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
