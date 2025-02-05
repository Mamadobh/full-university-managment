package com.global.university.role;

import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;

@Builder
public record RoleRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotBlank(message = "role name is required", groups = Default.class)
        @NotNull(message = "role name is required", groups = Default.class)
        String name

) {
}
