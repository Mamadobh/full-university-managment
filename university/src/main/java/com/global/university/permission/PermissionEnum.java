package com.global.university.permission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PermissionEnum {
    VIEW_TRACK,

    ADD_TRACK,
    DELETE_TRACK,
    UPDATE_TRACK,
    //=====================================//
    VIEW_LEVEL,
    ADD_LEVEL,
    DELETE_LEVEL,
    UPDATE_LEVEL,
    //=====================================//

    VIEW_DEPARTMENT,
    ADD_DEPARTMENT,
    DELETE_DEPARTMENT,
    UPDATE_DEPARTMENT;

}
