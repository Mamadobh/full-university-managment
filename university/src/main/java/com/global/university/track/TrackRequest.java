package com.global.university.track;

import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TrackRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotBlank(message = "name is required",groups = Default.class)
        @NotNull(message = "name is required",groups = Default.class)
        String name,
        String description,
        @NotNull(message = "departmentId is required",groups = Default.class)
        Integer departmentId
) {
}
