package com.global.university.role;

import lombok.Builder;

import java.util.Set;

@Builder
public record RoleRequest(
        Integer id,
        String name,
        Set<Integer> permissions

) {
}
