package com.global.university.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SideMenuEnum {
    DASHBOARD("dashboard", "dashboard", "dashboard"),
    TRACK("track", "dashboard", "dashboard"),
    LEVEL("StudyPlan", "dashboard", "study-plan"),
    ROLE_AND_PERMISSION("Roles & Permissions", "dashboard", "Roles&&Permissions");
    private final String label;
    private final String icon;
    private final String route;


}
