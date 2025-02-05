package com.global.university.permission;

import com.global.university.level.Level;
import com.global.university.resource.Resource;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

public class PermissionSpecification {

    public static Specification<Permission> permissionLike(String resourceLike) {

        return (root, query, cb) -> {
            Path<Resource> resource = root.get("resource");
            return cb.and(
                    cb.like(resource.get("name"), resourceLike),
                    cb.like(root.get("name"), "VIEW_%")
            );
        };
    }
}
