package com.global.university.typeSbj;

import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TypeRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotNull(message = "subject Type  is required", groups = Default.class)
        @NotBlank (message = "subject Type  is required", groups = Default.class)
        String subjectType
) {


}
