package com.global.university.coefficient;

import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotNull;

public record CoefficientRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotNull(message = "Coefficient is required", groups = Default.class)
        Double coefficient
        ) {


}
