package com.global.university.test;

import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotNull;

public record TestRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,

        @NotNull (message = "test Type id   is required", groups = Default.class)
        Integer testTypeId,
        @NotNull (message = "test coffiecient id   is required", groups = Default.class)
        Integer coefficientId,
        @NotNull (message = "test duration id   is required", groups = Default.class)
        Integer testDuraionId
) {


}
