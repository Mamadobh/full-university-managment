package com.global.university.moduleType;

import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ModuleTypeRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotBlank(message = "modulle type is required", groups = Default.class)
        @NotNull(message = "module type is required", groups = Default.class)
        String type
        ) {


}
