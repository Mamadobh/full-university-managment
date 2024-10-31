package com.global.university.student;

import com.global.university.customValidation.OneOfTwoFieldRequired;
import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@OneOfTwoFieldRequired(groups = Default.class, firstField = "cin", secondField = "passport_number", message = "Either cin or passport number  must be provided")
public record StudentRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotBlank(message = "firstname is required", groups = Default.class)
        @NotNull(message = "firstname is required", groups = Default.class)
        String firstname,
        @NotBlank(message = "lastname is required", groups = Default.class)
        @NotNull(message = "lastname is required", groups = Default.class)

        String lastname,
        String cin,
        String passport_number,
        @NotBlank(message = "Nationality is required", groups = Default.class)
        @NotNull(message = "Nationality is required", groups = Default.class)

        String nationality,
        @NotBlank(message = "num_tel is required", groups = Default.class)
        @NotNull(message = "num_tel is required", groups = Default.class)

        String num_tel,
        @NotNull(message = "date Of Birth is required", groups = Default.class)
        LocalDate dateOfBirth,
        @NotBlank(message = "place of birth is required", groups = Default.class)
        @NotNull(message = "place of birth is required", groups = Default.class)
        String place_of_birth,
        @NotBlank(message = "email is required", groups = Default.class)
        @NotNull(message = "email is required", groups = Default.class)

        String email,
        @NotBlank(message = "diploma is required", groups = Default.class)
        @NotNull(message = "diploma is required", groups = Default.class)

        String diploma,
        @NotBlank(message = "registered number is required", groups = Default.class)
        @NotNull(message = "registered number is required", groups = Default.class)
        String registered_number,
        @NotBlank(message = "status is required", groups = Default.class)
        @NotNull(message = "status is required", groups = Default.class)
        String status
) {

}
