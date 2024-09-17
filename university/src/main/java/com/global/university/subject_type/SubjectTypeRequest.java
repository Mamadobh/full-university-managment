package com.global.university.subject_type;

import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SubjectTypeRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotNull(message = "type id  is required", groups = Default.class)
        Integer typeId,
        @NotNull(message = "subject id  is required", groups = Default.class)
        Integer subjectId,
        @NotNull(message = "number of session  id  is required", groups = Default.class)
        Integer numberSessionId

) {


}
