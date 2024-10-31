package com.global.university.role;

import com.global.university.common.Mapper;
import com.global.university.permission.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleMapper implements Mapper<Role, Integer, RoleRequest, RoleReponse> {
    @Override
    public Role toEntity(RoleRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return Role.builder()
                .id(id)
                .name(request.name())
                .permissions(
                        request.permissions().stream()
                                .map(per -> Permission.builder().id(per)
                                        .build()).collect(Collectors.toSet())
                )
                .build();
    }

    @Override
    public RoleReponse toResponse(Role entity) {
        return RoleReponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
