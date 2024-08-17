package com.global.university.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DepartmentRequest(

        @NotBlank(message = "name is required")
        @NotNull(message = "name is required")
        String name,
        @NotBlank(message = "name is required")
        @NotNull(message = "name is required")
        String description
) {

}
