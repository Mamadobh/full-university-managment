package com.global.university.resource;

import com.global.university.permission.PermissionResponse;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResourcesResponse {
    private Integer id;
    private String name;
    private Set<PermissionResponse> permissions;
}
