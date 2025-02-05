package com.global.university.role;

import com.global.university.base.BaseService;
import com.global.university.permission.Permission;
import com.global.university.permission.PermissionRepo;
import com.global.university.permission.PermissionSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServices extends BaseService<Role, Integer, RoleRequest, RoleResponse> {
    private final RoleRepo roleRepo;
    private final PermissionRepo permissionRepo;

    /*
    *  Specification<Level> filterLevels = Specification
                    .where(StringUtils.isBlank(specialityLikeFilter) ? null : specialityLike(specialityLikeFilter))
                    .and(StringUtils.isBlank(trackLikeFilter) ? null : trackLike(trackLikeFilter))
                    .and(StringUtils.isBlank(departmentLikeFilter) ? null : departmentLike(departmentLikeFilter));

    * */
    public Integer addPermissionsToRole(Integer roleId, List<Integer> permissionsId) {
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException(" Role not Found with id " + roleId + " Please verify !!"));
        Permission permission = null;
        boolean isPermissionView = false;
        for (Integer permId : permissionsId) {
            permission = permissionRepo.findById(permId)
                    .orElseThrow(() -> new EntityNotFoundException(" Permission not Found with id " + permId + " Please verify !!"));
            if (!role.getPermissions().contains(permission)) {
                role.addPermission(permission);
            }
            if (permission.getName().startsWith("View_")) {
                isPermissionView = true;
            }
        }
        if (!permissionsId.isEmpty()) {
            log.error("resources name ", permission.getResource().getName());
            log.error("resources name ", permission.getName());

            Specification<Permission> viewPermissionFilter = Specification
                    .where(
                            PermissionSpecification.permissionLike(
                                    permission.getResource().getName())
                    );
            if (!isPermissionView) {
                Permission viewPermission = permissionRepo.findOne(viewPermissionFilter)
                        .orElseThrow(
                                () -> new EntityNotFoundException(" View Permission not Found for this  resource  Please verify !!")
                        );
                role.addPermission(viewPermission);
            }
            roleRepo.save(role);
        }
        return roleId;
    }

    public Integer removePermissionsRole(Integer roleId, List<Integer> permissionsId) {
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException(" Role not Found with id " + roleId + " Please verify !!"));
        permissionsId.forEach((permId) -> {
            Permission permission = permissionRepo.findById(permId)
                    .orElseThrow(() -> new EntityNotFoundException(" Permission not Found with id " + permId + " Please verify !!"));
            if (role.getPermissions().contains(permission)) {
                role.removePermission(permission);
            }
        });
        roleRepo.save(role);
        return roleId;
    }


}
