package com.global.university.semester;

import com.global.university.module.ModuleRequest;
import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record SemesterStudyPlanRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotBlank(message = "name is required", groups = Default.class)
        @NotNull(message = "name is required", groups = Default.class)
        String name,
        String description,
        @NotNull(message = "levelId is required", groups = Default.class)
        Integer levelId,
        @NotNull(message = "start date is required", groups = Default.class)
        LocalDate startDate,
        @NotNull(message = "end date is required", groups = Default.class)
        LocalDate endDate,
        @NotNull(message = "List of modules is  required", groups = Default.class)
        @NotEmpty(message = "List of modules is  required", groups = Default.class)
        @Valid
        List<ModuleRequest> modules
) {


}