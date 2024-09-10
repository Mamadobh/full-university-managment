package com.global.university.sessionNumber;

import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SessionNumberRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotNull(message = "number of session is required", groups = Default.class)
        Double numberOfSession
) {


}
