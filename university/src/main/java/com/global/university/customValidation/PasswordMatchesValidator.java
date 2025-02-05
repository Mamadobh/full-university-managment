package com.global.university.customValidation;

import com.global.university.authentication.RegistrationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    String message;

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String password =  (String) new BeanWrapperImpl(value).getPropertyValue("password");
        String passwordConfirmation =  (String) new BeanWrapperImpl(value).getPropertyValue("passwordConfirmation");

        boolean isValid = password != null && password.equals(passwordConfirmation);
        if (!isValid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode("password")
                    .addConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode("passwordConfirmation")
                    .addConstraintViolation();
            context.disableDefaultConstraintViolation();
        }
        return isValid;
    }
}
