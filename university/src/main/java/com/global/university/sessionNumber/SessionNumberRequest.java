package com.global.university.testType;

import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TestTypeRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotNull(message = "test Type  is required", groups = Default.class)
        @NotBlank (message = "test Type  is required", groups = Default.class)
        String testType
) {


}
