package com.global.university.authentication;

import com.global.university.customValidation.OneOfTwoFieldRequired;
import com.global.university.customValidation.PasswordMatches;
import com.global.university.validationGroup.Default;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@OneOfTwoFieldRequired(groups = Default.class, firstField = "cin", secondField = "passportNumber", message = "Either cin or passport number  must be provided")
@PasswordMatches(groups = Default.class)
public record RegistrationRequest(
        String cin,
        String passportNumber,
        @NotBlank(message = "email is required", groups = Default.class)
        @NotNull(message = "email is required", groups = Default.class)
        String email,
        @NotBlank(message = "Password is required", groups = Default.class)
        @NotNull(message = "Password is required", groups = Default.class)
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,
        String passwordConfirmation,
        @NotNull(message = "type of registred user is required", groups = Default.class)
        Integer type
) {
}
