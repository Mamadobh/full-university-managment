package com.global.university.coefficient;

import com.global.university.base.BaseEntity;
import com.global.university.subject.Subject;
import com.global.university.test.Test;
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
public class Coefficient extends BaseEntity<Integer> {
    @Column(unique = true)
    private Double coefficient;

    @OneToMany(mappedBy = "coefficient")
    private Set<Subject> subjects;

    @OneToMany(mappedBy = "coefficient")
    private Set<Test> tests;

}
