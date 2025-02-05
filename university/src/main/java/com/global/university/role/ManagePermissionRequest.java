package com.global.university.role;

import com.global.university.validationGroup.Default;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record ManagePermissionRequest(
        @NotNull(message = "List of permission is required", groups = Default.class)
        Integer roleId,
        @NotNull(message = "List of permission is required", groups = Default.class)
        @NotEmpty(message = "List of permission is required", groups = Default.class)
        List<Integer> permissions
) {
}
