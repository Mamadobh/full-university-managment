package com.global.university.module;

import com.global.university.customValidation.OneOfTwoFieldRequired;
import com.global.university.subject.SubjectStudyPlanRequest;
import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
@OneOfTwoFieldRequired(groups = Default.class, firstField = "semesterId", secondField = "subjects", message = "Either semester id or subjects must be provided")
public record ModuleRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotBlank(message = "name is required", groups = Default.class)
        @NotNull(message = "name is required", groups = Default.class)
        String name,
        String description,
        Integer semesterId,
        @NotNull(message = "ModuleTypeId is required", groups = Default.class)
        Integer moduleTypeId,
        @Valid
        List<SubjectStudyPlanRequest> subjects
) {


}
