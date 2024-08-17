package com.global.university.track;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TrackRequest(
        @NotBlank(message = "name is required")
        @NotNull(message = "name is required")
        String name,
        String description,
        @NotNull(message = "departmentId is required")
        Integer departmentId
) {
}
