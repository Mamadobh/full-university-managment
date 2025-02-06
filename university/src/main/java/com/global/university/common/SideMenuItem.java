package com.global.university.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SideMenuItem {

    private String label;
    private String icon;
    private String route;

}
