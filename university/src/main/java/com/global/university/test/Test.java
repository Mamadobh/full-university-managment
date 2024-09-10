package com.global.university.test;

import com.global.university.base.BaseEntity;
import com.global.university.coefficient.Coefficient;
import com.global.university.subject.Subject;
import com.global.university.testDuration.TestDuration;
import com.global.university.testType.TestType;
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
@Table(name = "test",
        uniqueConstraints = @UniqueConstraint(columnNames = {"type_test_id", "coefficient_id", "test_duration_id"})
)
public class Test extends BaseEntity<Integer> {

    @ManyToOne
    @JoinColumn(name = "coefficient_id")
    private Coefficient coefficient;

    @ManyToOne
    @JoinColumn(name = "type_test_id")
    private TestType typeTest;

    @ManyToMany
    private Set<Subject> subjects;

    @ManyToOne
    @JoinColumn(name = "test_duration_id")
    private TestDuration testDuration;
}
