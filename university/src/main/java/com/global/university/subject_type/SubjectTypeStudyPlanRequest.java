package com.global.university.subject_type;

import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotNull;

public record SubjectTypeStudyPlanRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotNull(message = "type id  is required", groups = Default.class)
        Integer typeId,
        Integer subjectId,
        @NotNull(message = "number of session  id  is required", groups = Default.class)
        Integer numberSessionId

) {

}
