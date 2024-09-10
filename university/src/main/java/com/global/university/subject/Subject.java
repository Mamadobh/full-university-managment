package com.global.university.subject;

import com.global.university.base.BaseEntity;
import com.global.university.coefficient.Coefficient;
import com.global.university.module.Module;
import com.global.university.subject_type.SubjectType;
import com.global.university.test.Test;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Subject extends BaseEntity<Integer> {

    private String name;
    private String averageFormula;
    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    @ManyToOne
    @JoinColumn(name = "coefficient_id", nullable = false)
    private Coefficient coefficient;

    @ManyToMany
    @JoinTable(
            name = "subject_test",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id"))
    private Set<Test> tests;

    @OneToMany(mappedBy = "subject")
    private Set<SubjectType> subjectTypes;


}
