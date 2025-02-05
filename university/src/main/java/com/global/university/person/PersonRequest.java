package com.global.university.person;

import com.global.university.customValidation.OneOfTwoFieldRequired;
import com.global.university.validationGroup.Default;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
@Builder
@OneOfTwoFieldRequired(groups = Default.class, firstField = "cin", secondField = "passportNumber", message = "Either cin or passport number  must be provided")
public record PersonRequest(@NotBlank(message = "firstname is required", groups = Default.class)
                            String firstname,
                            @NotBlank(message = "lastname is required", groups = Default.class)
                            String lastname,
                            String cin,
                            String passportNumber,
                            @NotBlank(message = "nationality is required", groups = Default.class)
                            String nationality,
                            @NotBlank(message = "num_tel is required", groups = Default.class)
                            String num_tel,
                            @NotNull(message = "dateOfBirth is required", groups = Default.class)

                            LocalDate dateOfBirth,
                            @NotBlank(message = "place_of_birth is required", groups = Default.class)

                            String place_of_birth,
                            @NotNull(message = "email is required", groups = Default.class)
                            String email) {
}
