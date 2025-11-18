package com.example.umc9th.global.validator;

import com.example.umc9th.domain.store.exception.code.RegionErrorCode;
import com.example.umc9th.domain.store.repository.RegionRepository;
import com.example.umc9th.global.annotation.ExistRegion;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegionExistValidator implements ConstraintValidator<ExistRegion, Long> {

    private final RegionRepository regionRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = regionRepository.existsById(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(RegionErrorCode.NOT_FOUND.getMessage()).addConstraintViolation();
        }
        return isValid;
    }
}
