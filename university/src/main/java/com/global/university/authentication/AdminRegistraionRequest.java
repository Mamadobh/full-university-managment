package com.global.university.authentication;

import com.global.university.customValidation.OneOfTwoFieldRequired;
import com.global.university.customValidation.PasswordMatches;
import com.global.university.person.PersonRequest;
import com.global.university.validationGroup.Default;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Set;

@Builder
@PasswordMatches(groups = Default.class)
@OneOfTwoFieldRequired(groups = Default.class, firstField = "person", secondField = "personId", message = "Either person details  or person id  must be provided")
public record AdminRegistraionRequest(
        @Valid
        PersonRequest person,
        Integer personId,
        @NotBlank(message = "Password is required", groups = Default.class)
        @NotNull(message = "Password is required", groups = Default.class)
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,
        String passwordConfirmation,
        @NotNull(message = "You must give at least one role ", groups = Default.class)
        @NotEmpty(message = "You must give at least one role ", groups = Default.class)
        Set<Integer> roles) {
}
