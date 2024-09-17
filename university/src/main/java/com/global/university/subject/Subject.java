package com.global.university.subject;

import com.global.university.base.BaseEntity;
import com.global.university.coefficient.Coefficient;
import com.global.university.module.Module;
import com.global.university.subject_type.SubjectType;
import com.global.university.subject_type.SubjectTypeStudyPlanRequest;
import com.global.university.test.Test;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.List;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subject_test", joinColumns = @JoinColumn(name = "subject_id"), inverseJoinColumns = @JoinColumn(name = "test_id"))
    private Set<Test> tests;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<SubjectType> subjectTypes;

    public static boolean checkDuplicationSubject(List<SubjectStudyPlanRequest> subjects) {
        Set<String> subjectsName = new HashSet<>();
        for (SubjectStudyPlanRequest subject : subjects) {
            if (!subjectsName.add(subject.name())) {
                return true;
            }
        }
        return false;

    }

    public static boolean checkDuplicateSubjectType(List<SubjectTypeStudyPlanRequest> subjectTypes) {
        Set<Integer> types = new HashSet<>();
        for (SubjectTypeStudyPlanRequest subjectType : subjectTypes) {
            if (!types.add(subjectType.typeId())) {
                return true;
            }
        }
        return false;
    }

}
