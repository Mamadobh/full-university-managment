package com.global.university.module;

import com.global.university.base.BaseEntity;
import com.global.university.subject.Subject;
import com.global.university.moduleType.ModuleType;
import com.global.university.semester.Semester;
import jakarta.persistence.*;
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
public class Module extends BaseEntity<Integer> {
    @Column(unique = true)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @OneToMany(mappedBy = "module")
    private Set<Subject> subjects;

    @ManyToOne
    @JoinColumn(name = "module_type_id", nullable = false)
    private ModuleType moduleType;
}
