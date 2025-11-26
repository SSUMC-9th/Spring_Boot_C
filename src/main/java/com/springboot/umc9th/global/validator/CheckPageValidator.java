package com.springboot.umc9th.global.validator;


import com.springboot.umc9th.global.annotation.CheckPage;
import com.springboot.umc9th.global.apiPayload.code.GeneralErrorCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        if (value == null || value < 1) {


            context.disableDefaultConstraintViolation();


            context.buildConstraintViolationWithTemplate(GeneralErrorCode.PAGE_NOT_VALID.toString())
                    .addConstraintViolation();

            return false;
        }
        return true;
    }
}
