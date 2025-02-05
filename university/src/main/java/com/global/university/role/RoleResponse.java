package com.global.university.role;

import com.global.university.permission.PermissionResponse;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponse {
    private Integer id;
    private  String name;
    private Set<PermissionResponse> permissions;
}
