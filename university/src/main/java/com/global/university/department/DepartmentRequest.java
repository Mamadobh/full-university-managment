package com.global.university.department;

import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DepartmentRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,

        @NotBlank(message = "name is required",groups = Default.class)
        @NotNull(message = "name is required",groups = Default.class)
        String name,
        @NotBlank(message = "description is required",groups = Default.class)
        @NotNull(message = "description is required",groups = Default.class)
        String description
) {

}
