package com.global.university.resource;

import com.global.university.common.Mapper;
import com.global.university.permission.Permission;
import com.global.university.permission.PermissionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResourcesMapper implements Mapper<Resource, Integer, ResourcesRequest, ResourcesResponse> {


    private final PermissionMapper permissionMapper;

    @Override
    public Resource toEntity(ResourcesRequest request, boolean isUpdate) {

        Integer id = isUpdate ? request.id() : null;

        Resource resource = Resource.builder()
                .id(id)
                .name(request.name())
                .build();

        Set<Permission> permissions = request.permissions().stream()
                .map((permission) -> permissionMapper.toEntity(permission,resource, isUpdate))
                .collect(Collectors.toSet());
        resource.setPermissions(permissions);
        return resource;
    }

    @Override
    public ResourcesResponse toResponse(Resource entity) {
        return ResourcesResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .permissions(entity.getPermissions().stream().map(permissionMapper::toResponse).collect(Collectors.toSet()))
                .build();
    }


}
