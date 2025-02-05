package com.global.university.resource;

import com.global.university.base.BaseEntity;
import com.global.university.permission.Permission;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Resource extends BaseEntity<Integer> {
    private String name;
    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private Set<Permission> permissions;

}
