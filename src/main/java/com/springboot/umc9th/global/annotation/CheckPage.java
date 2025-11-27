package com.springboot.umc9th.global.annotation;

import com.springboot.umc9th.global.validator.CheckPageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckPageValidator.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {

    String message() default "페이지 번호는 1 이상이어야 합니다."; // 에러 메시지
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}