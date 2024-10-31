package com.global.university.permission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PermissionEnum {
    READ_USER("user::read"),
    WRITE_USER("user::write"),
    DELETE_USER("user::delete"),
    UPDATE_USER("user:update");
    @Getter
    private final String permision;

}
