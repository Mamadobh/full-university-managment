package com.global.university.authentication;

import com.global.university.customValidation.OneOfTwoFieldRequired;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Builder;

@Builder
@OneOfTwoFieldRequired(groups = Default.class,firstField = "cin",secondField ="passportNumber", message = "Either semester id or subjects must be provided")
public record RegisterRequest(
        String cin,
        String passportNumber,
        @NotBlank(message = "email is required", groups = com.global.university.validationGroup.Default.class)
        @NotNull(message = "email is required", groups = com.global.university.validationGroup.Default.class)
        String email,
        String password,
        String passwordConfirmation,
        @NotNull(message = "type of registred user is required", groups = com.global.university.validationGroup.Default.class)
        Integer type
) {
}
