package com.global.university.authentication;

import com.global.university.validationGroup.Default;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AuthenticationRequest(
        @NotBlank(message = "email is required", groups = Default.class)
        @NotNull(message = "email is required", groups = Default.class)
        String email,
        @NotBlank(message = "Password is required", groups = Default.class)
        @NotNull(message = "Password is required", groups = Default.class)
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password

) {
}
