package com.global.university.permission;

import com.global.university.common.Mapper;
import com.global.university.resource.Resource;
import org.springframework.stereotype.Service;

@Service
public class PermissionMapper implements Mapper<Permission, Integer, PermissionRequest, PermissionResponse> {


    @Override
    public Permission toEntity(PermissionRequest request, boolean isUpdate) {

        Integer id = isUpdate ? request.id() : null;

        return Permission.builder()
                .id(id)
                .name(request.name())
                .build();
    }

    @Override
    public PermissionResponse toResponse(Permission entity) {
        return PermissionResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public Permission toEntity(PermissionRequest request, Resource resource, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return Permission.builder()
                .id(id)
                .name(request.name())
                .resource(resource)
                .build();
    }

}
