package com.global.university.role;

import com.global.university.common.Mapper;
import com.global.university.permission.Permission;
import com.global.university.permission.PermissionMapper;
import com.global.university.permission.PermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.join;

@Service
@RequiredArgsConstructor
public class RoleMapper implements Mapper<Role, Integer, RoleRequest, RoleResponse> {
    private final PermissionMapper permissionMapper;

    @Override
    public Role toEntity(RoleRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return Role.builder()
                .id(id)
                .name(join("_", request.name().toUpperCase().split(" ")))
                .build();
    }

    @Override
    public RoleResponse toResponse(Role entity) {
        return RoleResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .permissions(entity.getPermissions().stream()
                        .map(permissionMapper::toResponse)
                        .collect(Collectors.toSet()))
                .build();
    }
}
