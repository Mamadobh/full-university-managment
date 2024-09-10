package com.global.university.customValidation;

import com.global.university.common.DateRangeEntity;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Valid;

import java.time.LocalDate;

import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, DateRangeEntity> {
    private String message;

    @Override
    public void initialize(ValidDateRange constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }
    @Override
    public boolean isValid(DateRangeEntity entity, ConstraintValidatorContext context) {
        if (entity == null) {
            return true;
        }
        LocalDate startDate = entity.getStartDate();
        LocalDate endDate = entity.getEndDate();

        if (startDate == null || endDate == null) {
            return true;
        }

        boolean isValid = startDate.isBefore(endDate);

        if (!isValid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode("startDate")
                    .addConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode("endDate")
                    .addConstraintViolation();
            context.disableDefaultConstraintViolation();
        }
        return isValid;
    }



}
