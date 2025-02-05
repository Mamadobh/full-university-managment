package com.global.university.resource;

import com.global.university.permission.PermissionEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.global.university.permission.PermissionEnum.*;

@RequiredArgsConstructor
public enum ResourcesEnum {
    Department(
            Set.of(
                    VIEW_DEPARTMENT,
                    DELETE_DEPARTMENT,
                    UPDATE_DEPARTMENT,
                    ADD_DEPARTMENT
            )
    ),
    Track(
            Set.of(
                    VIEW_TRACK,
                    DELETE_TRACK,
                    UPDATE_TRACK,
                    ADD_TRACK
            )
    ),
    Level(
            Set.of(
                    VIEW_LEVEL,
                    DELETE_LEVEL,
                    UPDATE_LEVEL,
                    ADD_LEVEL
            )
    );
    @Getter
    private final Set<PermissionEnum> permissions;
}
