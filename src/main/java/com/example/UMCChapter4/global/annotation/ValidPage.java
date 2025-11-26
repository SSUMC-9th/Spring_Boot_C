package com.example.UMCChapter4.global.annotation;

import com.example.UMCChapter4.global.validator.PageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageValidator.class)
@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPage {
    //여기서 디폴트 메시지를 설정합니다.
    String message() default "pageNumber는 1보다 커야합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}