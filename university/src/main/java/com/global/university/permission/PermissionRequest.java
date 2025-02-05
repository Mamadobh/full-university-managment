package com.global.university.permission;

import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PermissionRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotNull(message = "name of permission is required",groups = Default.class)
        String name
) {

}
