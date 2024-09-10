package com.global.university.moduleType;

import com.global.university.base.BaseEntity;
import com.global.university.module.Module;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class ModuleType extends BaseEntity<Integer> {
    @Column(unique = true)
    private String type;

    @OneToMany(mappedBy = "moduleType", cascade = CascadeType.ALL)
    private Set<Module> modules;

}
