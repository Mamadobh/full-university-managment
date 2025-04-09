package com.global.university.resource;

import com.global.university.permission.PermissionEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.global.university.permission.PermissionEnum.*;

@Getter
@RequiredArgsConstructor
public enum ResourcesEnum {
    DEPARTMENT(
            Set.of(
                    VIEW_DEPARTMENT,
                    DELETE_DEPARTMENT,
                    UPDATE_DEPARTMENT,
                    ADD_DEPARTMENT
            )
    ),
    TRACK(
            Set.of(
                    VIEW_TRACK,
                    DELETE_TRACK,
                    UPDATE_TRACK,
                    ADD_TRACK
            )
    ),
    LEVEL(
            Set.of(
                    VIEW_LEVEL,
                    DELETE_LEVEL,
                    UPDATE_LEVEL,
                    ADD_LEVEL
            )
    ),

    ROLE_AND_PERMISSION(
            Set.of(
                    ADD_ROLE,
                    UPDATE_ROLE,
                    DELETE_ROLE,
                    VIEW_ROLE_AND_PERMISSION,
                    ADD_PERMISSION_TO_ROLE,
                    REMOVE_PERMISSION_FROM_ROLE
            )
    );
    private final Set<PermissionEnum> permissions;
}
