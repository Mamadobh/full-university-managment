package com.global.university.resource;

import com.global.university.permission.PermissionRequest;
import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;

@Builder
public record ResourcesRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotNull(message = "name of resource is required", groups = Default.class)
        String name,
        @NotNull(message = "permissions is required", groups = Default.class)
        Set<PermissionRequest> permissions
) {

}
