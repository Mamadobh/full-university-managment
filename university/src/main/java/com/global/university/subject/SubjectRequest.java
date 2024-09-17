package com.global.university.subject;

import com.global.university.subject_type.SubjectTypeStudyPlanRequest;
import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record SubjectRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotNull(message = "subject name   is required", groups = Default.class)
        @NotBlank(message = "subject name  is required", groups = Default.class)
        String name,
        @NotNull(message = "module id  is required", groups = Default.class)
        Integer moduleId,
        @NotNull(message = "coefficient id  is required", groups = Default.class)
        Integer coefficientId,
        @NotNull(message = "coefficient  id  is required", groups = Default.class)
        @NotEmpty(message = "tests  is required", groups = Default.class)
        List<Integer> tests



) {


}
