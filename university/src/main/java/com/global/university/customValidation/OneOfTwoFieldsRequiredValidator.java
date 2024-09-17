package com.global.university.customValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapperImpl;

import java.util.List;
import java.util.Set;


@Slf4j
public class OneOfTwoFieldsRequiredValidator implements ConstraintValidator<OneOfTwoFieldRequired, Object> {
    private String firstField;
    private String secondField;
    private String message;


    @Override
    public void initialize(OneOfTwoFieldRequired constraintAnnotation) {
        this.firstField = constraintAnnotation.firstField();
        this.secondField = constraintAnnotation.secondField();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Object firstFieldValue = new BeanWrapperImpl(value).getPropertyValue(this.firstField);
            Object secondFieldValue = new BeanWrapperImpl(value).getPropertyValue(this.secondField);

            boolean isFirstFieldValid = firstFieldValue != null && !firstFieldValue.toString().isEmpty();

            boolean isSecondFieldValid = secondFieldValue instanceof List && !((List<?>) secondFieldValue).isEmpty();
            boolean isValid = isFirstFieldValid || isSecondFieldValid;
            if (!isValid) {
                context.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(firstField)
                        .addConstraintViolation();
                context.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(secondField)
                        .addConstraintViolation();
                context.disableDefaultConstraintViolation();
            }
            return isValid;

        } catch (Exception e) {
            return false;

        }

    }
}
