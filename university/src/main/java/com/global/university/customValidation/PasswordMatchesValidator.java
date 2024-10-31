package com.global.university.customValidation;

import com.global.university.authentication.RegistrationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegistrationRequest> {
    String message;

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(RegistrationRequest request, ConstraintValidatorContext context) {
        boolean isValid = request.password() != null && request.password().equals(request.passwordConfirmation());
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
