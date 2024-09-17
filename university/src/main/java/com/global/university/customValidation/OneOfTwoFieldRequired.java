package com.global.university.customValidation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = OneOfTwoFieldsRequiredValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OneOfTwoFieldRequired {

    String message() default "One Of the Two field is required";

    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};
    String firstField();

    String secondField();
}
