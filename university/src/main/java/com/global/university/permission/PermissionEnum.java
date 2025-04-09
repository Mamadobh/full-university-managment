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
    UPDATE_DEPARTMENT,
    //==================================================//
        ADD_ROLE,
        DELETE_ROLE,
        UPDATE_ROLE,
        VIEW_ROLE_AND_PERMISSION,
        ADD_PERMISSION_TO_ROLE,
        REMOVE_PERMISSION_FROM_ROLE;


}
