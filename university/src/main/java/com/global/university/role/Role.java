package com.global.university.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.global.university.base.BaseEntity;
import com.global.university.permission.Permission;
import com.global.university.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Internal;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(
        name = "role-permissions-graph",
        attributeNodes = @NamedAttributeNode("permissions")
)
@Entity
public class Role extends BaseEntity<Integer> {

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @OrderBy("id ASC")
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }

    public void removePermission(Permission permission) {
        this.permissions.remove(permission);
    }
}
