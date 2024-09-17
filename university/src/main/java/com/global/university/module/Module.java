package com.global.university.module;

import com.global.university.base.BaseEntity;
import com.global.university.moduleType.ModuleType;
import com.global.university.semester.Semester;
import com.global.university.subject.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Module extends BaseEntity<Integer> {
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private Set<Subject> subjects;

    @ManyToOne
    @JoinColumn(name = "module_type_id", nullable = false)
    private ModuleType moduleType;

    public static boolean checkDuplicationModule(List<ModuleRequest> modules) {
        Set<String> modulesName = new HashSet<>();
        for (ModuleRequest module : modules) {
            if (!modulesName.add(module.name())) {
                return true;
            }
        }
        return false;
    }

}
