package com.global.university.testDuration;

import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotNull;

public record TestDurationRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotNull(message = "test duration  is required", groups = Default.class)
        Double testDuration
) {


}
