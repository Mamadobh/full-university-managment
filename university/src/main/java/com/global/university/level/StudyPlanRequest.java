package com.global.university.level;

import com.global.university.semester.SemesterStudyPlanRequest;
import com.global.university.validationGroup.Default;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record StudyPlanRequest(
        @NotNull(message = "semesters is requires", groups = Default.class)
        @NotEmpty(message = "semesters is required", groups = Default.class)
        @Valid
        List<SemesterStudyPlanRequest> semesters
) {
}
